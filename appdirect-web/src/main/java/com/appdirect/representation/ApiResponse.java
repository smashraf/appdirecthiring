package com.appdirect.representation;


import java.util.List;

public class ApiResponse<T> {
 private int responseCode;
    private String message;
    private List<T> representation;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getRepresentation() {
        return representation;
    }

    public void setRepresentation(List<T> representation) {
        this.representation = representation;
    }
}
