package io.rainforest.banana.app.service.demo;

import io.rainforest.banana.app.dto.demo.VehicleDetails;
import org.springframework.stereotype.Service;

@Service
public class UserVehicleService {
    public VehicleDetails getVehicleDetails(String sboot) {
        VehicleDetails vehicleDetails = new VehicleDetails("Honda", "Civic");
        return vehicleDetails;
    }
}
