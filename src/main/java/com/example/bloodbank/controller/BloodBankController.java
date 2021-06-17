package com.example.bloodbank.controller;

import com.example.bloodbank.BloodBank;
import com.example.bloodbank.service.BloodBankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloodbank")
public class BloodBankController {

    private BloodBankService  bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService=bloodBankService;
    }



    @GetMapping()
    public List<BloodBank> getBloodBankOfController() {
        List<BloodBank> bloodBankOfService = bloodBankService.getBloodBankOfService();
        return bloodBankOfService;
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
        List<BloodBank> allByAddressAndBloodGroupOfService = bloodBankService.findAllByAddressAndBloodGroupOfService(address, bloodGroup);
        return allByAddressAndBloodGroupOfService;
    }


    @PostMapping()
    public void addBloodBank(@RequestBody BloodBank bloodbank) {
        bloodBankService.add(bloodbank);
    }



    @PutMapping()
    public void updateBloodBank(@RequestBody BloodBank bloodbank) {
        bloodBankService.update(bloodbank);

    }

    @DeleteMapping()
    public void deleteBloodBank(@RequestBody BloodBank bloodbank) {
        bloodBankService.delete(bloodbank);

    }
}

