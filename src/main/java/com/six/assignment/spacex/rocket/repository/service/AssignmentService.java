package com.six.assignment.spacex.rocket.repository.service;


import com.six.assignment.spacex.rocket.repository.domain.Mission;
import com.six.assignment.spacex.rocket.repository.domain.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.StatusMissionEnum;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AssignmentService {
    private final RocketService rocketService;
    private final MissionService missionService;

    public void assignRocketsToMission(List<Rocket> rockets, Mission mission) {
        throw new RuntimeException("not implemented yet");
    }

    public void assignRocketToMission(String rocketName, String missionName) {
        Rocket rocket = rocketService.getRocket(rocketName);
        Mission mission = missionService.getMission(missionName);
        if (rocket.getMission() != null)
            throw new IllegalStateException("Rocket has already been assigned to a mission");
        if (mission.getStatus().equals(StatusMissionEnum.ENDED))
            throw new IllegalStateException("Cannot assign to mission with status ended");

        rocket.setMission(mission);
        mission.assignRocket(rocket);
    }
}
