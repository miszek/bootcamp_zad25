package com.michalszekalski.bootcamp_zad25;

public enum Category {
    HOUSEHOLD ("household"),
    WORK ("work"),
    TRAINING ("training"),
    OTHER ("other");

    String catDesc;

    Category(String catDesc) {
        this.catDesc = catDesc;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }
}
