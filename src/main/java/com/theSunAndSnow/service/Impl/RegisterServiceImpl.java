package com.theSunAndSnow.service.Impl;

import com.theSunAndSnow.repository.CustomerRepository;
import com.theSunAndSnow.repository.impl.CustomerRepositoryImpl;
import com.theSunAndSnow.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public Object register(String name, String sex, String telephone, String password) {
        return customerRepository.register(name, sex, telephone, password);
    }
}
