package com.asher.im.base;

import lombok.Data;

/**
 *
 * @author : coderzyj
 * @date : 2019/1/7 15:44
 * @Version : v1.0
 * @description  API格式封装
 **/
@Data
public class ApiResponse {
    private int code;
    private String message;
    private Object data;
    private boolean more;

    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }
    public static ApiResponse ofMessage(int code,String message){
        return new ApiResponse(code,message,null);
    }

    public static ApiResponse ofSuccess(Object data){
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(),data);
    }

    public static ApiResponse ofFailed(Object data,String message){
        return new ApiResponse(Status.BAD_REQUEST.getCode(),message,data);
    }

    public static ApiResponse ofFailed(){
        return new ApiResponse(Status.BAD_REQUEST.getCode(),"",null);
    }
    public static ApiResponse ofStatus(Status status){
        return new ApiResponse(status.getCode(),status.getStandardMessage(),null);
    }
    public enum Status{
        SUCCESS(200,"OK"),
        BAD_REQUEST(400,"Bad Request"),
        INTERNAL_SERVER_ERROR(500,"UnKnown Internal Error"),
        NOT_VALID_PARAM(40005,"Not valid Params"),
        NOT_SUPPORTED_OPERATION(40006,"Operation not supported"),
        NOT_LOGIN(50000,"Not Login");

        private int code;
        private String standardMessage;


        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }
    }
}
