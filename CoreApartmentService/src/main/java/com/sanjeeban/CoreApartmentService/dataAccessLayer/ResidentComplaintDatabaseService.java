package com.sanjeeban.CoreApartmentService.dataAccessLayer;


import com.sanjeeban.CoreApartmentService.customException.InvalidComplaintCodeException;
import com.sanjeeban.CoreApartmentService.customException.InvalidWorkflowException;
import com.sanjeeban.CoreApartmentService.entity.*;
import com.sanjeeban.CoreApartmentService.jdbc.JdbcUtil;
import com.sanjeeban.CoreApartmentService.repository.*;
import jakarta.persistence.AttributeOverride;
import jakarta.transaction.Transactional;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class ResidentComplaintDatabaseService {

    @Autowired
    ResidentProfileRepository residentProfileRepository;

    @Autowired
    ComplaintTypeMasterRepository complaintTypeMasterRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    WorkflowStatusMasterRepository workflowStatusMasterRepository;

    @Autowired
    WorkflowProcessRepository workflowProcessRepository;

    @Autowired
    WorkflowHistoryRepository workflowHistoryRepository;

    @Autowired
    JdbcUtil jdbcUtil;

    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired
    ComplaintTechnicianMappingRepository ComplaintTechnicianMappingRepository;




    public boolean checkResidentExistence(Long residentId) {
        return residentProfileRepository.existsByresidentId(residentId);
    }

    public Long getComplaintDetailsFromTypeMaster(String complaintCode){
        ComplaintTypeMaster complaintTypeMaster = complaintTypeMasterRepository.getComplaintByComplaintCode(complaintCode).orElseThrow(
                () ->  new InvalidComplaintCodeException("Complaint code '"+complaintCode+"' does not exist"));

        return complaintTypeMaster.getComplaintTypeId();


    }


    public void insertDataIntoTComplaint(Long complaintId, String residentId, Long complaintTypeId, String complaintDescription) {
        Complaint currComplaint = new Complaint();
        currComplaint.setComplaintId(complaintId);
        currComplaint.setResidentId(Long.valueOf(residentId));
        currComplaint.setComplaintTypeId(complaintTypeId);
        currComplaint.setDescription(complaintDescription);
        complaintRepository.save(currComplaint);
    }

    public void insertDataIntoTWorkflowProcess(Long workflowId, Long complaintId, String statusId) {

        // getting the workflow status code ffrom status id.
        String statusCode = workflowStatusMasterRepository.getStatuscodeFromStatusId(Long.valueOf(statusId));
        if(statusCode==null){
            throw new InvalidWorkflowException("Workflow status Id does not conform to standards.");
        }

        WorkflowProcess workflowProcess = new WorkflowProcess();
        workflowProcess.setWorkflowId(workflowId);
        workflowProcess.setComplaintId(complaintId);
        workflowProcess.setStatusId(Long.valueOf(statusId));
        workflowProcessRepository.save(workflowProcess);
    }

    public void insertDataIntoTWorkflowHistory(Long workflowId, Long complaintId, String workflowStatus) {
        WorkflowHistory workflowHistory = new WorkflowHistory();
        workflowHistory.setWorkflowId(workflowId);
        workflowHistory.setComplaintId(complaintId);
        workflowHistory.setStatusId(Long.valueOf(workflowStatus));
        workflowHistoryRepository.save(workflowHistory);
    }

    public Long getCurrWorkflowStatusOfComplaintId(Long complaintId) {

        Long statusId = 0L;

        final String sql = "select t.status_id from estatedb.t_workflow_process t\n" +
                "where t.complaint_id = ?";

        try(Connection conn = jdbcUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,complaintId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    statusId = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception in jdbc -2"+e);
        }
        return statusId;
    }

    public boolean checkIfComplaintExists(Long complaintId){
        return complaintRepository.existsById(complaintId);
    }


    public int updateWorkflowStatus(Long complaintId, Long statusId, Long userId) {

        final String sql = """
        UPDATE estatedb.t_workflow_process
        SET status_id = ?
        WHERE complaint_id = ?
        """;
        int rowsUpdated = 0;

        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, statusId);
            ps.setLong(2, complaintId);

            rowsUpdated = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to update workflow status for complaintId: " + complaintId, e
            );
        }
        return rowsUpdated;
    }

    public void checkAndInsertIntoWorkflowHistory(Long complaintId, Long statusId) {
        // get the workflow id from t_workflow_process;
        Long workflowId = 0L;
        Optional<WorkflowProcess> workflowProcessObj = workflowProcessRepository.getWorkflowDetailsFromComplaintId(complaintId);
        if(workflowProcessObj.isPresent()){
            workflowId = workflowProcessObj.get().getWorkflowId();
        }

        insertDataIntoTWorkflowHistory(workflowId,complaintId,String.valueOf(statusId));
    }

    public boolean checkIfTechnicianExists(Long technicianId) {
        return technicianRepository.existsById(technicianId);
    }

    public void insertIntoTechnicianComplaintMappingTable(Long technicianId, Long complaintId) {
        ComplaintTechnicianMapping complaintTechnicianMapping = new ComplaintTechnicianMapping();
        complaintTechnicianMapping.setTechnicianId(technicianId);
        complaintTechnicianMapping.setComplaintId(complaintId);
        ComplaintTechnicianMappingRepository.save(complaintTechnicianMapping);
    }
}
