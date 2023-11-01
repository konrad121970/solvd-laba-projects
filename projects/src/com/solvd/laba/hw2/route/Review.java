package com.solvd.laba.hw2.route;

public class Review {
    private Integer numberOfStars;
    private String content;

    public Review(Integer numberOfStars, String content) {
        this.numberOfStars = numberOfStars;
        this.content = content;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
