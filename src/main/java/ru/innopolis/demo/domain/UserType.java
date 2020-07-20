package ru.innopolis.demo.domain;

public enum UserType {
    //TODO: Нужны комментарии
    ADMIN("Администратор"),
    SELLER("Продавец"),
    CUSTOMER("Покупатель"),
    COURIER("Курьер");

    private String title;

    UserType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
