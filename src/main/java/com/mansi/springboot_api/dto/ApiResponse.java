package com.mansi.springboot_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    @JsonProperty("is_success")
    private boolean success;

    @JsonProperty("official_email")
    private String officialEmail;

    @JsonProperty("data")
    private Object data;

    public ApiResponse(boolean success, String officialEmail, Object data) {
        this.success = success;
        this.officialEmail = officialEmail;
        this.data = data;
    }

    // GETTERS
    public boolean isSuccess() {
        return success;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public Object getData() {
        return data;
    }
}
