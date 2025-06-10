package com.six.assignment.spacex.rocket.repository.domain.rocket;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class RocketService {

    private final MissionService missionService;

    private final Map<String, Rocket> rockets = new HashMap<>();

    public void addNewRocket(String name) {
        if (rockets.containsKey(name)) throw new IllegalArgumentException("Rocket already exists");
        rockets.put(name, new Rocket(name));
    }

    public void changeRocketStatus(String rocketName, StatusRocketEnum status) {
        Rocket rocket = rockets.get(rocketName);
        if (StatusRocketEnum.IN_REPAIR.equals(status)){
            Mission mission = missionService.getMissionByRocketName(rocketName);
            if (null!=mission){
                rocket.setStatus(status);
                missionService.changeMissionStatus(mission.getName(), StatusMissionEnum.PENDING);
            }
        }
        rocket.setStatus(status);
    }


    public Rocket getRocket(String name){
        return rockets.get(name);
    }

}
