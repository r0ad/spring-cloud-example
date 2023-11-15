package io.rainforest.banana.app.web.demo;

import io.rainforest.banana.app.dto.demo.VehicleDetails;
import io.rainforest.banana.app.service.demo.UserVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVehicleController {
    @Autowired
    private UserVehicleService userVehicleService;

    @GetMapping("/sboot/vehicle")
    public String test(String sboot) {
        VehicleDetails vehicleDetails = userVehicleService.getVehicleDetails(sboot);
        System.out.println(vehicleDetails);
        return "Honda Civic";
    }
}
