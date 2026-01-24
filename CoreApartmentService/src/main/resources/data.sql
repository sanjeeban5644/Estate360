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


INSERT INTO estatedb.t_apartment
(apartment_id, block_no, flat_no, bhk_type, carpet_area, availability)
VALUES
(02334, 'B1', '1A', '2BHK', 850.0, true),
(02335, 'B1', '1B', '3BHK', 1200.0, false),
(02336, 'B1', '2A', '1BHK', 650.0, true),
(02337, 'B1', '2B', '2BHK', 900.0, true),
(02338, 'B2', '1A', '3BHK', 1250.0, false),
(02339, 'B2', '1B', '1BHK', 640.0, true),
(02340, 'B2', '2A', '2BHK', 880.0, true),
(02341, 'B2', '2B', '3BHK', 1180.0, false),
(02342, 'C1', '1A', '1BHK', 600.0, true),
(02343, 'C1', '1B', '2BHK', 870.0, true),
(02344, 'C1', '2A', '3BHK', 1300.0, false),
(02345, 'C1', '2B', '1BHK', 620.0, true),
(02346, 'C2', '1A', '2BHK', 890.0, true),
(02347, 'C2', '1B', '3BHK', 1220.0, false),
(02348, 'C2', '2A', '1BHK', 650.0, true);


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
