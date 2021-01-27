package com.unlimint.task.bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "amount", "comment", "filename", "line", "result"})
public class OrderInvalidResultDto extends OrderResultDto {
    private String id;
    private String amount;
    private String comment;
    private String filename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String fileName) {
        this.filename = fileName;
    }

    public OrderInvalidResultDto() {}

    public OrderInvalidResultDto(String id, String amount, String comment, String filename, Integer line, String result) {
        super(line, result);
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
    }
}
