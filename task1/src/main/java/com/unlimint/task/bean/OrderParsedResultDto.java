package com.unlimint.task.bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "amount", "comment", "filename", "line", "result"})
public class OrderParsedResultDto  extends OrderResultDto {
    private Integer id;
    private BigDecimal amount;
    private String comment;
    private String filename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public OrderParsedResultDto() {}

    public OrderParsedResultDto(Integer id, BigDecimal amount, String comment, String filename, Integer line, String result) {
        super(line, result);
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
    }
}
