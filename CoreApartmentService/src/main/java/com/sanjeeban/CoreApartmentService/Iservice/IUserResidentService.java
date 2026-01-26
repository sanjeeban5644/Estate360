package com.sanjeeban.CoreApartmentService.Iservice;


import com.sanjeeban.CoreApartmentService.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserResidentService {


    public SaveUserResidentResponse saveNewUserService(SaveNewUserRequest request);

    public List<GetAllUsersResponse> getAllUsers(int page, int size);

    public SaveUserResidentResponse saveNewResident(SaveNewResidentRequest request);

    public List<GetAllResidentsResponse> getAllResidents();
}
