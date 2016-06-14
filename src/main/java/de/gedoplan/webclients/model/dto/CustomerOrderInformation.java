package de.gedoplan.webclients.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerOrderInformation {

    private Long orderCount;

    @JsonIgnore
    private Double totalAmount;

    private Double discount;

    public CustomerOrderInformation() {
    }

    public CustomerOrderInformation(Long orderCount, Double totalAmount) {
        this.orderCount = orderCount;
        this.totalAmount = totalAmount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
