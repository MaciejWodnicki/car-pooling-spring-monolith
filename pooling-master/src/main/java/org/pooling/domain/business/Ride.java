package org.pooling.domain.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String driverName;
    @NotNull
    private String origin;
    @NotNull
    private String destination;
    @NotNull
    private LocalDateTime departureTime;
    @NotNull
    private int availableSpots;

    // Constructors
    public Ride() {}

    public Ride(int id, String driverName, String origin, String destination, LocalDateTime departureTime, int availableSpots) {
        this.id = id;
        this.driverName = driverName;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSpots = availableSpots;
    }

    // Getters and Setters

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public int getAvailableSpots() { return availableSpots; }
    public void setAvailableSpots(int availableSpots) { this.availableSpots = availableSpots; }


}
