package com.example.bloodbank.controller;

import com.example.bloodbank.BloodBank;
import com.example.bloodbank.BloodBankRepository;
import com.example.bloodbank.exception.ResourceNotFoundException;
import com.example.bloodbank.exception.ValueMissingException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bloodbank")
public class BloodBankController {
    private BloodBankRepository bloodBankRepository;

    public BloodBankController(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }

    @GetMapping()
    public List<BloodBank> getBloodBank() {
        List<BloodBank> bloodBanks = new ArrayList<>();
        for (BloodBank bloodbank : bloodBankRepository.findAll()) {
            bloodBanks.add(bloodbank);
        }

        return bloodBanks;
    }

    /* @GetMapping("/{address}/{bloodGroup}")
    public List<BloodBank> getBloodBankInformation(@PathVariable("address") String address
            , @PathVariable("bloodGroup") String bloodGroup) {
        List<BloodBank> allByAddressAndBloodGroup = bloodBankRepository.findAllByAddressAndBloodGroup(address, bloodGroup);
        return allByAddressAndBloodGroup;
    }
*/
    @GetMapping("/{address}/{bloodGroup}")
    public List<BloodBank> getBlooodBankInformation(@PathVariable("address") String address
            , @PathVariable("bloodGroup") String bloodGroup) {
        List<BloodBank> allByAddressAndBloodGroup = bloodBankRepository.findAllByAddressAndBloodGroup(address, bloodGroup);
        if (allByAddressAndBloodGroup.isEmpty()) {
            throw new ResourceNotFoundException("no available bloodgroop");
        }
        return allByAddressAndBloodGroup;
    }


    @PostMapping()
    public void addBloodBank(@RequestBody BloodBank bloodbank) {
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

    @PutMapping()
    public void updateBloodBank(@RequestBody BloodBank bloodbank) {
        bloodBankRepository.save(bloodbank);
    }

    @DeleteMapping()
    public void deleteBloodBank(@RequestBody BloodBank bloodbank) {
        bloodBankRepository.delete(bloodbank);
    }
}

