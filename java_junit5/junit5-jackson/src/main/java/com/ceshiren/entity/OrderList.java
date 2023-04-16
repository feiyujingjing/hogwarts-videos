package com.ceshiren.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class OrderList {
    @JsonProperty("item")
    private  String otherItem;
    @JsonProperty("quantity")
    private  String qua;
    @JsonProperty("unitPrice")
    private  String price;
    @JsonProperty("orderDate")
    private LocalDate date;

    public OrderList() {
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public void setQua(String qua) {
        this.qua = qua;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOtherItem() {
        return otherItem;
    }

    public String getQua() {
        return qua;
    }

    public String getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "otherItem='" + otherItem + '\'' +
                ", qua='" + qua + '\'' +
                ", price='" + price + '\'' +
                ", date=" + date +
                '}';
    }

    public OrderList(String otherItem, String qua, String price, LocalDate date) {
        this.otherItem = otherItem;
        this.qua = qua;
        this.price = price;
        this.date = date;
    }
}
