package com.bohniman.cid.appapi.exception;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomException
 */
public class CustomException {

    private String message;
    private HttpStatus httpStatus;
    private Date timestamp;

    public CustomException() {
    }

    public CustomException(String message, HttpStatus httpStatus, Date timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public CustomException message(String message) {
        this.message = message;
        return this;
    }

    public CustomException httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public CustomException timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomException)) {
            return false;
        }
        CustomException customException = (CustomException) o;
        return Objects.equals(message, customException.message)
                && Objects.equals(httpStatus, customException.httpStatus)
                && Objects.equals(timestamp, customException.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, httpStatus, timestamp);
    }

    @Override
    public String toString() {
        return "{" + " message='" + getMessage() + "'" + ", httpStatus='" + getHttpStatus() + "'" + ", timestamp='"
                + getTimestamp() + "'" + "}";
    }

}