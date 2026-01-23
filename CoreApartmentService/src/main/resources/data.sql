INSERT INTO t_role_master (role_id, role_name) VALUES
(231, 'Admin'),
(232, 'Manager'),
(233, 'Resident'),
(234, 'Technician'),
(235, 'Security');

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
