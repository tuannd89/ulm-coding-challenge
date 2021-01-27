package com.unlimint.task.bean;

public class OrderResultDto {
    private Integer line;
    private String result;

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public OrderResultDto() {
    }

    public OrderResultDto(Integer line, String result) {
        this.line = line;
        this.result = result;
    }
}
