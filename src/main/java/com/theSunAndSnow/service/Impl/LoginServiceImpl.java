package com.theSunAndSnow.service.Impl;

import com.theSunAndSnow.repository.CouponRepository;
import com.theSunAndSnow.repository.CustomerRepository;
import com.theSunAndSnow.repository.impl.CouponRepositoryImpl;
import com.theSunAndSnow.repository.impl.CustomerRepositoryImpl;
import com.theSunAndSnow.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private CouponRepository couponRepository = new CouponRepositoryImpl();

    @Override
    public Object login(String telephone, String password) {
            return customerRepository.login(telephone, password);
    }

    @Override
    public Integer getCouponNum(Integer customerId) {
        return couponRepository.getCouponNum(customerId);
    }


}
