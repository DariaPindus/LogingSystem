package com.daria.sprimg.mvc.service;

import com.daria.sprimg.mvc.model.Address;
import com.daria.sprimg.mvc.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddresses(){
        return addressRepository.getAddresses();
    }

    public List<String> getCountries() {
        return addressRepository.getCountries();
    }
}
