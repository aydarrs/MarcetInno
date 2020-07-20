package ru.innopolis.demo.domain;

public enum OrderStatus {
    //TODO: Нужны комментарии
    NEW("новый"),
    QUESTION_TO_STORE("вопрос магазину"),
    QUESTION_TO_CUSTOMER("вопрос клиенту"),
    AGREED("согласован"),
    COME_COMPLETED("комплектуется"),
    COMPLETED("скомплектован"),
    COURIER("передан на доставку"),
    EXECUTED("выполнен"),
    ARRIVED_SORTING_POINT("прибыл на сортировочный пункт"),
    FINISHED("выполнен"),
    RETURN("возврат"),
    EXCELLENT("отменен");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
