package com.theSunAndSnow.entity;

public class Coupon {
    private Integer id;
    private Integer customerId;
    private Integer couponNum;

    public Coupon() {
    }

    public Coupon(Integer id, Integer customerId, Integer couponNum) {
        this.id = id;
        this.customerId = customerId;
        this.couponNum = couponNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }
}
