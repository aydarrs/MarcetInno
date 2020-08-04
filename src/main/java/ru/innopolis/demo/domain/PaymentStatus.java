package ru.innopolis.demo.domain;

public enum PaymentStatus {
    NOT_PAID("Не оплачен"),
    PAID("Оплачен");

    String title;

    PaymentStatus(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
