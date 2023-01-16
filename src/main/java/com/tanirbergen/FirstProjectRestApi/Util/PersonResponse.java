package com.tanirbergen.FirstProjectRestApi.Util;

public class PersonResponse {
    private String message;
    private long datatime;

    public PersonResponse(String message, long datatime) {
        this.message = message;
        this.datatime = datatime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDatatime() {
        return datatime;
    }

    public void setDatatime(long datatime) {
        this.datatime = datatime;
    }
}
