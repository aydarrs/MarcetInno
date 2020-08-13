package ru.innopolis.demo.domain;

public enum DeliveryMethod {
    //TODO: Добавить способы доставки (убрать лишние, оставить только те,
    // что нужны на конкретном этапе проекта

    //TODO: ДОбавить аннотации и комментарии
    CAR("Автомобиль"),
    FOOT("Пеший");

    private String title;

    DeliveryMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
