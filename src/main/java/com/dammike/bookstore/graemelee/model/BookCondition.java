package com.dammike.bookstore.graemelee.model;

import javax.persistence.Embeddable;

@Embeddable
enum BookCondition {
    POOR("have few marks and wear"),
    LIKE_NEW("in great shape and acceptable condition"),
    EXCELLENT("as good as new");

    private final String status;

    BookCondition(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
