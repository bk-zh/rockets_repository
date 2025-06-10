package com.six.assignment.spacex.rocket.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonRocketsRepository {

    private final Map<String, Rocket> rockets = new HashMap<>();
    private final Map<String, Mission> missions = new HashMap<>();

    public void addNewRocket(String name) {
        if (rockets.containsKey(name)) throw new IllegalArgumentException("Rocket already exists");
        rockets.put(name, new Rocket(name));
    }

    public void assignRocketToMission(String rocketName, String missionName) {
        Rocket rocket = rockets.get(rocketName);
        Mission mission = missions.get(missionName);
        if (rocket.getMission() != null) throw new IllegalStateException("Rocket has already been assigned to a mission");
        if (mission.getStatus().equals(StatusMissionEnum.ENDED))
            throw new IllegalStateException("Cannot assign to mission with status ended");

        rocket.setMission(mission);
        mission.assignRocket(rocket);
    }

    public void changeRocketStatus(String rocketName, StatusRocketEnum status) {
        Rocket rocket = rockets.get(rocketName);
        rocket.setStatus(status);
    }

    public void addNewMission(String missionName) {
        if (missions.containsKey(missionName)) throw new IllegalArgumentException("Mission already exists");
        missions.put(missionName, new Mission(missionName));
    }

    public void assignRocketsToMission(List<Rocket> rockets, Mission mission) {
        throw new RuntimeException("not implemented yet");
    }

    public void changeMissionStatus(String missionName, StatusMissionEnum status) {
        Mission mission = missions.get(missionName);
        mission.setStatus(status);
    }

    public List<Mission> getMissionsSummary() {
        throw new RuntimeException("not implemented yet");
    }


}