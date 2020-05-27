package com.theSunAndSnow.service;

public interface LoginService {
    public Object login(String telephone, String password);
    public Integer getCouponNum(Integer customerId);
}
