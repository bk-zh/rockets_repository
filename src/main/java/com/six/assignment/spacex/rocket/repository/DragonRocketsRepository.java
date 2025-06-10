package com.six.assignment.spacex.rocket.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonRocketsRepository {

    private final Map<String, Rocket> rockets = new HashMap<>();
    private final Map<String, Mission> missions = new HashMap<>();

    public void addNewRocket(String name) {
        if (rockets.containsKey(name)) throw new IllegalArgumentException("Rocket already exists");
        rockets.put(name, new Rocket(name, StatusRocketEnum.ON_GROUND));
    }

    public void assignRocketToMission(String rocketName, String missionName) {
        throw new RuntimeException("not implemented yet");
    }

    public void changeRocketStatus(String rocketName, StatusRocketEnum status) {
        throw new RuntimeException("not implemented yet");
    }

    public void addNewMission(String missionName) {
        if (rockets.containsKey(missionName)) throw new IllegalArgumentException("Rocket already exists");
        missions.put(missionName, new Mission(missionName));
    }

    public void assignRocketsToMission(List<Rocket> rockets, Mission mission) {
        throw new RuntimeException("not implemented yet");
    }

    public void changeMissionStatus(Mission mission, StatusMissionEnum status) {
        throw new RuntimeException("not implemented yet");
    }

    public List<Mission> getMissionsSummary() {
        throw new RuntimeException("not implemented yet");
    }


}