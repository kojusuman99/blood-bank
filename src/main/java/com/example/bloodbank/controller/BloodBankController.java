package com.example.bloodbank.controller;

import com.example.bloodbank.BloodBank;
import com.example.bloodbank.BloodBankRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bloodbank")
public class BloodBankController {
    private BloodBankRepository bloodBankRepository;

    public BloodBankController(BloodBankRepository bloodBankRepository) { this.bloodBankRepository = bloodBankRepository;}
    @GetMapping()
    public List<BloodBank> getBloodBank() {
        List<BloodBank>  bloodBanks = new ArrayList<>();
        for (BloodBank bloodbank : bloodBankRepository.findAll()) {
            bloodBanks.add(bloodbank);
        }

        return bloodBanks;
    }
    @GetMapping("/{address}/{bloodGroup}")
    public List<BloodBank> getRegisterInformation(@PathVariable("address") String address
            ,@PathVariable("bloodGroup")String bloodGroup) {
        List<BloodBank> allByAddressAndBloodGroup = bloodBankRepository.findAllByAddressAndBloodGroup(address,bloodGroup);
        return allByAddressAndBloodGroup;
    }
    @PostMapping()
    public void addBloodBank(@RequestBody BloodBank bloodbank) { bloodBankRepository.save(bloodbank);
    }

    @PutMapping()
    public void updateBloodBank(@RequestBody BloodBank bloodbank) {
        bloodBankRepository.save(bloodbank);
    }

    @DeleteMapping()
    public void deleteBlookBank(@RequestBody BloodBank bloodbank) {bloodBankRepository.delete(bloodbank);}
    }

