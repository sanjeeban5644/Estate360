package com.sanjeeban.CoreApartmentService.dto;

public class GeneralResponse {
    private String responseCode;
    private String responseMsg;
    private String remarks;

    public GeneralResponse() {
    }

    public GeneralResponse(String responseCode, String responseMsg, String remarks) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.remarks = remarks;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
