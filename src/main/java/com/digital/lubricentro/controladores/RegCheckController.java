package com.digital.lubricentro.controladores;

import com.digital.lubricentro.servicios.RegCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegCheckController {

    @Autowired
    private RegCheckService regCheckService;

    @GetMapping("/vehicle-details")
    public ResponseEntity<String> getVehicleDetails(
            @RequestParam String registrationNumber,
            @RequestParam String countryCode,
            @RequestParam String username) {
        try {
            String response = regCheckService.getVehicleDetails(registrationNumber, countryCode, username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
