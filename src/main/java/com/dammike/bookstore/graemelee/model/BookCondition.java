package com.dammike.bookstore.graemelee.model;

public enum BookCondition {
    POOR("have few marks and wear"),
    LIKE_NEW("in great shape and acceptable condition"),
    EXCELLENT("as good as new");

    private String status;

    BookCondition() {
    }

    BookCondition(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
