package ru.innopolis.demo.domain;

public enum OrderStatus {

    CREATED("новый", 0),
    COURIER_APPOINTED("курьер назначен", 1),
    DELIVERING("передан курьеру", 2),
    COMPLETED("доставлен", 3);

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
