package com.unlimint.task.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByPosition;
import com.unlimint.task.validation.IntegerFormat;
import com.unlimint.task.validation.NumberFormat;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCSVToBean {
    @CsvBindByPosition(position = 0)
    @IntegerFormat
    @NotNull
    private String orderId;

    @CsvBindByPosition(position = 1)
    @NumberFormat
    private String amount;

    @CsvBindByPosition(position = 3)
    private String comment;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
