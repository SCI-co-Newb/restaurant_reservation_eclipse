package com.example.hello.restaurant_reservation_eclipse;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface SampleService {

    @WebMethod
    public String sayHello(String name);
}
