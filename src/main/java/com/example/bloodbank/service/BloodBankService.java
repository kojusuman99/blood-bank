package com.example.bloodbank.service;

import com.example.bloodbank.BloodBank;
import com.example.bloodbank.repository.BloodBankRepository;
import com.example.bloodbank.exception.ResourceNotFoundException;
import com.example.bloodbank.exception.ValueMissingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloodBankService {

    private BloodBankRepository bloodBankRepository;

    public BloodBankService(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }


    public List<BloodBank> getBloodBankOfService() {
        List<BloodBank> bloodBanks = new ArrayList<>();
        for (BloodBank bloodbank : bloodBankRepository.findAll()) {
            bloodBanks.add(bloodbank);
        }

        return bloodBanks;
    }

    public List<BloodBank> findAllByAddressAndBloodGroupOfService(String address,String bloodGroup) {
        List<BloodBank> allByAddressAndBloodGroup = bloodBankRepository.findAllByAddressAndBloodGroup(address, bloodGroup);
        if (allByAddressAndBloodGroup.isEmpty()) {
            throw new ResourceNotFoundException("no available bloodgroop");
        }
        return allByAddressAndBloodGroup;

    }


    public void  add(BloodBank bloodbank) {
        validation(bloodbank);
        bloodBankRepository.save(bloodbank);
    }
    private void validation(BloodBank bloodbank) {
        if (bloodbank.getName().equals("")) {
            throw new ValueMissingException("Name is missing ");
        }
        if (bloodbank.getBloodGroup().equals("")) {
            throw new ValueMissingException("BLOOD GROUP IS MISSING ");
        }
        if (!bloodbank.getBloodGroup().equalsIgnoreCase("A positive") && !bloodbank.getBloodGroup().equalsIgnoreCase("A negative")
                && !bloodbank.getBloodGroup().equalsIgnoreCase("B positive") &&  !bloodbank.getBloodGroup().equalsIgnoreCase(" B negative")
                &&   !bloodbank.getBloodGroup().equalsIgnoreCase(" O negative") &&   !bloodbank.getBloodGroup().equalsIgnoreCase(" O positive")
                &&   !bloodbank.getBloodGroup().equalsIgnoreCase(" AB negative") &&   !bloodbank.getBloodGroup().equalsIgnoreCase(" AB positive"))
        {
            throw new ValueMissingException("BLOOD GROUP IS WRONG ");
        }
    }
   public void update(BloodBank bloodbank)
   {
       bloodBankRepository.save(bloodbank);
   }
   public  void delete(BloodBank bloodbank){
       bloodBankRepository.delete(bloodbank);
   }

}
