package org.pooling.domain.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.pooling.domain.user.AppUser;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String driverName;

    @NotNull
    private String driverTelephone;

    @NotNull
    private String origin;

    @NotNull
    private String destination;

    @NotNull
    private LocalDateTime departureTime;

    @NotNull
    private int availableSpots;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ride_passengers",
            joinColumns = @JoinColumn(name = "ride_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> passengers = new HashSet<>();

    // Constructors
    public Ride() {}

    public Ride(String driverName, String driverTelephone, String origin, String destination,
                LocalDateTime departureTime, int availableSpots) {
        this.driverName = driverName;
        this.driverTelephone = driverTelephone;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSpots = availableSpots;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public Set<AppUser> getPassengers() {
        return new HashSet<>(passengers);
    }

    // Business methods
    public boolean addPassenger(AppUser user) {
        if (passengers.size() < availableSpots) {
            passengers.add(user);
            user.getRides().add(this); // Maintain bidirectional relationship
            return true;
        }
        return false;
    }

    public boolean removePassenger(AppUser user) {
        if (passengers.remove(user)) {
            user.getRides().remove(this); // Maintain bidirectional relationship
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return passengers.size() >= availableSpots;
    }

    public int getRemainingSpots() {
        return availableSpots - passengers.size();
    }

    // Display methods
    public String getRideDetails() {
        return String.format("%s → %s | Departure: %s | Driver: %s | Available spots: %d/%d",
                origin, destination,
                departureTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a")),
                driverName,
                getRemainingSpots(),
                availableSpots);
    }

    public String getPassengerList() {
        if (passengers.isEmpty()) {
            return "No passengers yet";
        }

        StringBuilder sb = new StringBuilder("Passengers:\n");
        passengers.forEach(passenger ->
                sb.append("- ").append(passenger.getFirstName()).append("\n"));

        return sb.toString();
    }

    @Override
    public String toString() {
        return getRideDetails() + "\n" + getPassengerList();
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }
}
