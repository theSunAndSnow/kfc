package com.theSunAndSnow.service.Impl;

import com.theSunAndSnow.repository.CustomerRepository;
import com.theSunAndSnow.repository.impl.CustomerRepositoryImpl;
import com.theSunAndSnow.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public Object login(String telephone, String password) {
            return customerRepository.login(telephone, password);
    }
}
