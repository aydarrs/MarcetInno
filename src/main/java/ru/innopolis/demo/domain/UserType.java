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

    public String getRole() {
        return "ROLE_" + this.toString();
    }

    public static UserType fromString(String title) {
        for (UserType b : UserType.values()) {
            if (b.title.equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

}
