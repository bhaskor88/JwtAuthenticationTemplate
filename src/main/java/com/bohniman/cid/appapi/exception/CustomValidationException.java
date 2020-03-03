package com.bohniman.cid.appapi.exception;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;

/**
 * CustomValidationException
 */
public class CustomValidationException {

    Map<String, String> errorMessages;
    private HttpStatus httpStatus;
    private Date timestamp;

    public CustomValidationException() {
    }

    public CustomValidationException(Map<String, String> errorMessages, HttpStatus httpStatus, Date timestamp) {
        this.errorMessages = errorMessages;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrorMessages() {
        return this.errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public CustomValidationException errorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
        return this;
    }

    public CustomValidationException httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public CustomValidationException timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomValidationException)) {
            return false;
        }
        CustomValidationException customValidationException = (CustomValidationException) o;
        return Objects.equals(errorMessages, customValidationException.errorMessages)
                && Objects.equals(httpStatus, customValidationException.httpStatus)
                && Objects.equals(timestamp, customValidationException.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessages, httpStatus, timestamp);
    }

    @Override
    public String toString() {
        return "{" + " errorMessages='" + getErrorMessages() + "'" + ", httpStatus='" + getHttpStatus() + "'"
                + ", timestamp='" + getTimestamp() + "'" + "}";
    }

}
