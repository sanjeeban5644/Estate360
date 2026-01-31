package com.sanjeeban.CoreApartmentService.service;


import com.sanjeeban.CoreApartmentService.Iservice.IResidentComplaintService;
import com.sanjeeban.CoreApartmentService.dataAccessLayer.ResidentComplaintDatabaseService;
import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.dto.AssignComplaint;
import com.sanjeeban.CoreApartmentService.dto.RegisterComplaint;
import com.sanjeeban.CoreApartmentService.dto.RegisterComplaintResponse;
import com.sanjeeban.CoreApartmentService.dto.RegisterOrRejectComplaint;
import com.sanjeeban.CoreApartmentService.repository.ComplaintTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ComplaintService implements IResidentComplaintService {

    @Autowired
    ResidentComplaintDatabaseService residentComplaintDatabaseService;

    @Autowired
    UserResidentDatabaseService userResidentDatabaseService;

    @Override
    public RegisterComplaintResponse registerNewComplaint(RegisterComplaint request) {
        RegisterComplaintResponse response = new RegisterComplaintResponse();

        // first verify whether the resident exists or not.
        String residentId = request.getResidentId();
        boolean isResidentPresent = residentComplaintDatabaseService.checkResidentExistence(Long.valueOf(residentId));
        if(!isResidentPresent){
            response.setResponseCode("400");
            response.setResponseMsg("Resident is non-existential. Please verify.");
            return response;
        }
        //resident exists. Verification of complaint code.
        String complaintCode = request.getComplaintCode();
        Long complaintTypeId = residentComplaintDatabaseService.getComplaintDetailsFromTypeMaster(complaintCode);

        //generate complaint id.
        Long complaintId = generateComplaintId(residentId,complaintTypeId);

        residentComplaintDatabaseService.insertDataIntoTComplaint(complaintId,residentId,complaintTypeId,request.getComplaintDescription());

        // generating workflow.
        //generating workflow id.
        Long workflowId = generateWorkflowId(complaintId);
        //since this is the first time workflow is being generated, so the status ID IS 101.
        //saving into tworkflowprocess.
        residentComplaintDatabaseService.insertDataIntoTWorkflowProcess(workflowId,complaintId,"101");

        residentComplaintDatabaseService.insertDataIntoTWorkflowHistory(workflowId,complaintId,"101");

        response.setComplaintId(String.valueOf(complaintId));
        response.setCurrentStatus("New Complaint Registered");
        response.setResponseCode("200");
        response.setResponseMsg("Complaint successfully received.");

        return response;
    }

    @Override
    public String registerOrRejectComplaint(RegisterOrRejectComplaint request) {

        String userId = request.userId();
        // check if  userid is valid or not.
        boolean isValidUser = userResidentDatabaseService.checkUser(Long.valueOf(userId));
        String role = "";
        if(isValidUser){
            role = userResidentDatabaseService.getRoleFromUserId(Long.valueOf(userId));
        }else{
            return "User Id is not valid.";
        }
        if(!role.equals("ADMIN")) return "User Id is not authorized to perform this activity.";


        if(!request.acceptRejectFlag().equals("ACCEPT") && !request.acceptRejectFlag().equals("REJECT")){
            return "Please enter valid accept reject flag.";
        }
        // checking  if the complaint id exists.
        boolean doesComplaintExist = residentComplaintDatabaseService.checkIfComplaintExists(Long.valueOf(request.complaintId()));
        if(!doesComplaintExist) return "The given complaint id does not exist";

        // get the current workflow status of the complaint.
        Long currentStatus = residentComplaintDatabaseService.getCurrWorkflowStatusOfComplaintId(Long.valueOf(request.complaintId()));

        if(!(currentStatus == 101)){
            return "Complaint workflow has already proceeded this stage.";
        }

        // changing the workflow status to 102 -> pending with admin.
        // update the workflow status in workflow process table.
        int rowsUpdated = residentComplaintDatabaseService.updateWorkflowStatus(Long.valueOf(request.complaintId()),102L,Long.valueOf(request.userId()));
        // getting the workflow id and then inserting in the workflow history table.
        residentComplaintDatabaseService.checkAndInsertIntoWorkflowHistory(Long.valueOf(request.complaintId()),102L);
        if(rowsUpdated==1){
            // success
            return "Complaint has been successfully accepted.";
        }else{
            return "Complaint has been rejected.";
        }
    }

    @Override
    public String assignComplaint(AssignComplaint request) {
        Long userId = Long.valueOf(request.userId());
        Long complaintId = Long.valueOf(request.complaintId());
        Long technicianId = Long.valueOf(request.technicianId());

        boolean isUserValid =  userResidentDatabaseService.checkUser(userId);
        String role = "";
        if(isUserValid){
            role = userResidentDatabaseService.getRoleFromUserId(Long.valueOf(userId));
        }else{
            return "User Id is not valid.";
        }
        if(!role.equals("ADMIN")) return "User Id is not authorized to perform this activity.";

        boolean doesComplaintExist = residentComplaintDatabaseService.checkIfComplaintExists(complaintId);
        if(!doesComplaintExist) return "The given complaint id does not exist.";

        boolean doesTechnicianExist = residentComplaintDatabaseService.checkIfTechnicianExists(technicianId);
        if(!doesTechnicianExist) return "Technician Id is invalid.";

        // get the current workflow status of the complaint.
        Long currentStatus = residentComplaintDatabaseService.getCurrWorkflowStatusOfComplaintId(Long.valueOf(request.complaintId()));
        if(!(currentStatus==102)){
            return "Complaint assigning is not available at this workflow level.";
        }

        // changing the workflow status to 102 -> 103
        // update the workflow status in workflow process table.
        int rowsUpdated = residentComplaintDatabaseService.updateWorkflowStatus(complaintId,103L,complaintId);
        // getting the workflow id and then inserting in the workflow history table.
        residentComplaintDatabaseService.checkAndInsertIntoWorkflowHistory(complaintId,103L);

        // inserting the technician -> complaint mapping.

        residentComplaintDatabaseService.insertIntoTechnicianComplaintMappingTable(technicianId,complaintId);

        return "Task Assigned with Technician with id : "+String.valueOf(technicianId);
    }

    private Long generateWorkflowId(Long complaintId) {
        return Long.parseLong(complaintId + "01");
    }


    private Long generateComplaintId(String strResidentId, Long complaintTypeId) {
        Long residentId = Long.valueOf(strResidentId);

        LocalDateTime now = LocalDateTime.now();
        String dateTimeStr = now.format(DateTimeFormatter.ofPattern("yyMMddHH"));
        String combined = dateTimeStr+strResidentId+complaintTypeId;
        return Long.parseLong(combined);
    }
}
