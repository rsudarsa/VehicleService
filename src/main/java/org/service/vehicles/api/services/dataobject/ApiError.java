package org.service.vehicles.api.services.dataobject;

/**
 * Created by ramya on 18/2/17.
 */
public class ApiError {

    private int errorCode;
    private String errorMessage;

    public ApiError() {

    }

    public ApiError(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
    }

}

