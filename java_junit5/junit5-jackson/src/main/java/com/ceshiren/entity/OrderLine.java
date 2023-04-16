package com.ceshiren.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderLine {
    private String item;
    private int quantity;
    private BigDecimal unitPrice;
    private LocalDate orderDate;

    public OrderLine(String item, int quantity, BigDecimal unitPrice, LocalDate orderDate) {
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", orderDate=" + orderDate +
                '}';
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderLine() {
    }
}
