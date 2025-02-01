package com.codeloon.ems.util;

public class ResponseCode {
    private ResponseCode() {
        throw new UnsupportedOperationException("ResponseCode class cannot be instantiated");
    }
    public static final String RSP_SUCCESS = "00";
    public static final String RSP_ERROR   = "01";

}
