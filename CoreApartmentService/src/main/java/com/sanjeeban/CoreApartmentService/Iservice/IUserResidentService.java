package com.sanjeeban.CoreApartmentService.Iservice;


import com.sanjeeban.CoreApartmentService.dto.*;

import java.util.List;

public interface IUserResidentService {


    public SaveUserResidentResponse saveNewUserService(SaveNewUserRequest request);

    public List<GetAllUsersResponse> getAllUsers();

    public SaveUserResidentResponse saveNewResident(SaveNewResidentRequest request);

    public List<GetAllResidentsResponse> getAllResidents();
}
