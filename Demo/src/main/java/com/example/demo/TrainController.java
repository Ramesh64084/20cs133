package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @GetMapping
    public List<Train> getAllTrainsNext12Hours() {
        List<Train> trains = new ArrayList<>();

        // Simulate data for three sample trains
        trains.add(createSampleTrain("ABC001", "Express Train 1", "10:00", "14:00"));
        trains.add(createSampleTrain("XYZ123", "Superfast Train", "12:30", "18:45"));
        trains.add(createSampleTrain("DEF789", "Local Train 3", "15:15", "19:30"));

        return trains;
    }

    private Train createSampleTrain(String trainNumber, String trainName, String departureTime, String arrivalTime) {
        Train train = new Train();
        train.setTrainNumber(trainNumber);
        train.setTrainName(trainName);
        train.setDepartureTime(departureTime);
        train.setArrivalTime(arrivalTime);

        List<SeatAvailability> seatAvailabilityList = new ArrayList<>();
        Random random = new Random();
        for (String coachClass : new String[]{"Sleeper", "AC", "General"}) {
            int availableSeats = random.nextInt(50) + 50; // Random availability between 50 and 99
            double price = random.nextDouble() * 1000.0; // Random price up to 1000.0
            SeatAvailability seatAvailability = new SeatAvailability();
            seatAvailability.setCoachClass(coachClass);
            seatAvailability.setAvailableSeats(availableSeats);
            seatAvailability.setPrice(price);
            seatAvailabilityList.add(seatAvailability);
        }

        train.setSeatAvailabilityList(seatAvailabilityList);
        return train;
    }
}