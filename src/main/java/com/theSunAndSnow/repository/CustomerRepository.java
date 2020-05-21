package com.theSunAndSnow.repository;

public interface CustomerRepository {
    public Object login(String telephone, String password);

    public Object register(String name, String sex, String telephone, String password);
}
