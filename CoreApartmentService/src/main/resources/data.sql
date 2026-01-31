INSERT INTO t_role_master (role_id, role_name) VALUES
(231, 'ADMIN'),
(232, 'Manager'),
(233, 'RESIDENT'),
(234, 'Technician'),
(235, 'Security'),
(236, 'TENANT');

INSERT INTO estatedb.t_user_role (user_id, role_id) VALUES
(1001, 231),  -- User 1001 assigned ADMIN role
(1002, 233);

INSERT INTO t_complaint_type_master (complaint_type_id, complaint_code, complaint_desc) VALUES
(4001, 'ELEC', 'Electrical Issue'),
(4002, 'PLUMB', 'Plumbing Issue'),
(4003, 'WATER', 'Water Supply Problem'),
(4004, 'CLEAN', 'Cleaning / Housekeeping'),
(4005, 'SECUR', 'Security Concern'),
(4006, 'LIFT', 'Lift / Elevator Problem'),
(4007, 'PARK', 'Parking Issue');

INSERT INTO t_workflow_status_master (status_id, status_code, status_desc) VALUES
(101, 'NEW', 'New Complaint Registered'),
(102, 'PENDING', 'Pending with Admin'),
(103, 'ASSIGN', 'Technician Assigned. Work In Progress'),
(104, 'RESOLVED', 'Complaint Resolved'),
(105, 'CLOSED', 'Closed after Verification');
INSERT INTO t_workflow_status_master (status_id, status_code, status_desc)
VALUES (106, 'REJECTED', 'Complaint Rejected');

INSERT INTO estatedb.t_apartment (apartment_id, block_no, flat_no, bhk_type, carpet_area, availability)
VALUES
(1101, 'A', '101', '1BHK', 550.0, TRUE),
(1102, 'A', '102', '2BHK', 750.0, TRUE),
(1103, 'A', '103', '3BHK', 1050.0, FALSE),
(2201, 'B', '201', '2BHK', 800.0, TRUE),
(2202, 'B', '202', '1BHK', 600.0, FALSE),
(2203, 'B', '203', '3BHK', 1100.0, TRUE),
(3301, 'C', '301', '2BHK', 780.0, TRUE),
(3302, 'C', '302', '1BHK', 520.0, TRUE),
(3303, 'C', '303', '3BHK', 1200.0, FALSE),
(4401, 'D', '401', '2BHK', 850.0, TRUE),
(4402, 'D', '402', '1BHK', 580.0, TRUE),
(4403, 'D', '403', '3BHK', 1150.0, FALSE),
(5501, 'E', '501', '2BHK', 790.0, TRUE),
(5502, 'E', '502', '1BHK', 560.0, FALSE),
(5503, 'E', '503', '3BHK', 1250.0, TRUE),
(6601, 'F', '601', '2BHK', 820.0, TRUE),
(6602, 'F', '602', '1BHK', 540.0, TRUE),
(6603, 'F', '603', '3BHK', 1180.0, FALSE),
(7701, 'G', '701', '2BHK', 880.0, TRUE),
(7702, 'G', '702', '3BHK', 1300.0, TRUE);


-- Insert Script 1
INSERT INTO estatedb.t_user_account
    (user_id, name, email, mobile, status, user_name, password)
VALUES
    (1001, 'John Doe', 'john.doe@example.com', '9876543210', 'ACTIVE', 'user1', '2002');

-- Insert Script 2
INSERT INTO estatedb.t_user_account
    (user_id, name, email, mobile, status, user_name, password)
VALUES
    (1002, 'Jane Smith', 'jane.smith@example.com', '9123456780', 'INACTIVE', 'user2', '2003');

INSERT INTO estatedb.t_user_role (user_id, role_id) VALUES (1,231);


INSERT INTO estatedb.t_resident_profile
(resident_id, user_id, apartment_id, resident_grp_id, resident_type, dob, aadhar, previous_address, current_address)
VALUES
(101, 201, 301, 401, 'Owner', '1985-04-12', '123456789012', '12 Old Street, Kolkata', '45 New Avenue, Kolkata'),
(102, 202, 302, 402, 'Tenant', '1990-07-25', '234567890123', '56 Lake Road, Kolkata', '78 Green Park, Kolkata'),
(103, 203, 303, 403, 'Owner', '1978-11-05', '345678901234', '89 Hill View, Kolkata', '12 River Side, Kolkata');

INSERT INTO estatedb.t_technician (tech_id, tech_name, tech_mobile)
VALUES
(7602, 'Arjun Das', '9876543210'),
(7603, 'Ravi Kumar', '9123456780'),
(7604, 'Suman Roy', '9988776655'),
(7605, 'Priya Sen', '9345678901'),
(7606, 'Amit Sharma', '9456123789'),
(7607, 'Neha Gupta', '9567890123'),
(7608, 'Karan Mehta', '9678901234'),
(7609, 'Sneha Bose', '9789012345'),
(7610, 'Vikram Singh', '9890123456'),
(7611, 'Ananya Mukherjee', '9901234567');