package com.sanjeeban.CoreApartmentService.dto;

public record RegisterOrRejectComplaint(
        String userId,
        String complaintId,
        String acceptRejectFlag
) {
}

