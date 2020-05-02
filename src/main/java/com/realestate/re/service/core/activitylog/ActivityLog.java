package com.realestate.re.service.core.activitylog;

public class ActivityLog {

    private String activity;

    private String uri;

    private String method;

    private String remoteAddrl;

    private String username;

    private String dateTime;

    public ActivityLog(){}

    public ActivityLog(String activity , String uri , String method , String remoteAddrl, String username , String dateTime){
        this.activity = activity;
        this.uri = uri;
        this.method = method;
        this.remoteAddrl = remoteAddrl;
        this.username = username;
        this.dateTime = dateTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemoteAddrl() {
        return remoteAddrl;
    }

    public void setRemoteAddrl(String remoteAddrl) {
        this.remoteAddrl = remoteAddrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
