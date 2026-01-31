package com.sanjeeban.CoreApartmentService.controller;


import com.netflix.discovery.converters.Auto;
import com.sanjeeban.CoreApartmentService.Iservice.IResidentComplaintService;
import com.sanjeeban.CoreApartmentService.dto.AssignComplaint;
import com.sanjeeban.CoreApartmentService.dto.RegisterComplaint;
import com.sanjeeban.CoreApartmentService.dto.RegisterComplaintResponse;
import com.sanjeeban.CoreApartmentService.dto.RegisterOrRejectComplaint;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaint")
public class ComplaintWorkflowController {
//@Operation(summary = "This validates and saves a new User", description = "This validates and saves a new User")
//    @PostMapping(path="/saveNewUser")

    @Autowired
    IResidentComplaintService iResidentComplaintService;

    @Operation(summary = "This initiates a complaint workflow", description = "Complaint workflow initiation")
    @PostMapping(path="/initiateComplaint")
    public ResponseEntity<RegisterComplaintResponse> registerComplaint(@RequestBody RegisterComplaint request){
        RegisterComplaintResponse response = new RegisterComplaintResponse();
        response = iResidentComplaintService.registerNewComplaint(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "This lets admin user accepts a complaint", description = "Complaint workflow second stage")
    @PostMapping(path="/complaintRegistration")
    public ResponseEntity<String> registerOrRejectComplaint(@RequestBody RegisterOrRejectComplaint request){
        String response = iResidentComplaintService.registerOrRejectComplaint(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Assigns technician", description = "This api assigns the complaint to a technician")
    @PostMapping(path="/assignComplaint")
    public ResponseEntity<String> assignComplaintToTechnician(@RequestBody AssignComplaint request){
        String response = iResidentComplaintService.assignComplaint(request);
        return ResponseEntity.ok(response);
    }



}
