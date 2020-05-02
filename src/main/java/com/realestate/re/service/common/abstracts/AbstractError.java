package com.realestate.re.service.common.abstracts;

public class AbstractError {

    private boolean valid;

    private String message;

    private boolean versionUnmatched = false;

    public boolean isVersionUnmatched() {
        return versionUnmatched;
    }

    public void setVersionUnmatched(boolean versionUnmatched) {
        this.versionUnmatched = versionUnmatched;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
