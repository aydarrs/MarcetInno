package ru.innopolis.demo.domain;

public enum OrderStatus {

    CREATED("новый"),
    COURIER_APPOINTED("курьер назначен"),
    DELIVERING("передан курьеру"),
    COMPLETED("доставлен");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
