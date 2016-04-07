package com.gaincigarretprice.idiot.sun.model.data.dto;

/**
 * Created by ladmusician on 3/21/16.
 */
public class ResultDTO<T> {
    private int code;
    private String error;
    private T result;


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }
}
