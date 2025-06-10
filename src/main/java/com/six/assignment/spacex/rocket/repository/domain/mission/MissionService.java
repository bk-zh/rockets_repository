package com.six.assignment.spacex.rocket.repository.domain.mission;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MissionService {
    private final Map<String, Mission> missions = new HashMap<>();

    public void addNewMission(String missionName) {
        if (missions.containsKey(missionName)) throw new IllegalArgumentException("Mission already exists");
        missions.put(missionName, new Mission(missionName));
    }

    public void changeMissionStatus(String missionName, StatusMissionEnum status) {
        Mission mission = missions.get(missionName);
        mission.setStatus(status);
    }

    public Mission getMission(String name) {
        return missions.get(name);
    }


}
