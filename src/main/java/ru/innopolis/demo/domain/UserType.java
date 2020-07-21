package ru.innopolis.demo.domain;

public enum UserType {
    //TODO: Добавить роли (убрать лишние, оставиьт только те,
    // что нужны на конкретном этапе проекта

    //TODO: ДОбавить аннотации и комментарии
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
