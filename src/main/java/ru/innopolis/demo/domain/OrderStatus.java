package ru.innopolis.demo.domain;

public enum OrderStatus {

    CREATED("Новый", 0),
    COURIER_APPOINTED("Курьер назначен", 1),
    DELIVERING("Передан курьеру", 2),
    COMPLETED("Доставлен", 3);

    private String title;
    private int ind;

    OrderStatus(String title, int ind) {
        this.title = title;
        this.ind = ind;
    }

    public String getTitle() {
        return title;
    }

    public int getInd() {
        return ind;
    }
}
