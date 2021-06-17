package com.example.bloodbank.repository;

import com.example.bloodbank.BloodBank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends CrudRepository<BloodBank, Integer> {

    List<BloodBank> findAllByAddressAndBloodGroup(String address,String bloodGroup);
}
