package com.example.postearevised.Miscellaneous.Enums;

public enum OrderHistorySortEnum {
    TODAY_ENUM("Today"),
    THIS_WEEK_ENUM("This Week"),
    THIS_MONTH_ENUM("This Month"),
    THIS_YEAR_ENUM("This Year"),
    ALL_TIME_ENUM("All Time");

    private final String title;

    OrderHistorySortEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
