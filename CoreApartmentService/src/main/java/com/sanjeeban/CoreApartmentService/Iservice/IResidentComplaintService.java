package com.sanjeeban.CoreApartmentService.Iservice;

import com.sanjeeban.CoreApartmentService.dto.AssignComplaint;
import com.sanjeeban.CoreApartmentService.dto.RegisterComplaint;
import com.sanjeeban.CoreApartmentService.dto.RegisterComplaintResponse;
import com.sanjeeban.CoreApartmentService.dto.RegisterOrRejectComplaint;

public interface IResidentComplaintService {
    public RegisterComplaintResponse registerNewComplaint(RegisterComplaint request);

    public String registerOrRejectComplaint(RegisterOrRejectComplaint request);

    public String assignComplaint(AssignComplaint request);
}
