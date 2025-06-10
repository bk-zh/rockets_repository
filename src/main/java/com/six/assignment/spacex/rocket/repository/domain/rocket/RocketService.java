package com.six.assignment.spacex.rocket.repository.domain.rocket;

import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;

import java.util.HashMap;
import java.util.Map;


public class RocketService {

    private final Map<String, Rocket> rockets = new HashMap<>();
    public void changeRocketStatus(String rocketName, StatusRocketEnum status) {
        Rocket rocket = rockets.get(rocketName);
        rocket.setStatus(status);
    }

    public void addNewRocket(String name) {
        if (rockets.containsKey(name)) throw new IllegalArgumentException("Rocket already exists");
        rockets.put(name, new Rocket(name));
    }
    public Rocket getRocket(String name){
        return rockets.get(name);
    }

}
