package ru.innopolis.demo.domain;

public enum DeliveryMethod {
    //TODO: Нужны комментарии
    CAR("автомобиль"),
    FOOT("пеший");

    private String title;

    DeliveryMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
