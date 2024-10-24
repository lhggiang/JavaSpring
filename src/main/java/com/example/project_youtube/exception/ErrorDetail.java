package com.example.project_youtube.exception;

import java.util.List;

public class ErrorDetail {
    private String error;
    private List<String> errorDetail;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(List<String> errorDetail) {
        this.errorDetail = errorDetail;
    }
}
