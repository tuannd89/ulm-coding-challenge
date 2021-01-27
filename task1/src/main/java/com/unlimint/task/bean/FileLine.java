package com.unlimint.task.bean;

import java.util.concurrent.atomic.AtomicInteger;

public class FileLine {
    private Integer index;
    private String fileName;
    private String text;

    public FileLine(AtomicInteger index, String fileName, String text) {
        this.index = index.get();
        this.fileName = fileName;
        this.text = text;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
