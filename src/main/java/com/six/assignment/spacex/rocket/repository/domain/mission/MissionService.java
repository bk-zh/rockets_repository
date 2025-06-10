package com.six.assignment.spacex.rocket.repository.domain.mission;

import com.six.assignment.spacex.rocket.repository.domain.mission.state.MissionState;
import com.six.assignment.spacex.rocket.repository.domain.mission.state.MissionStateFactory;
import com.six.assignment.spacex.rocket.repository.domain.mission.state.ScheduledState;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MissionService {
    private final Map<String, Mission> missions = new HashMap<>();

    public void addNewMission(String missionName) {
        if (missions.containsKey(missionName)) throw new IllegalArgumentException("Mission already exists");
        Mission mission= new Mission(missionName);
        ScheduledState scheduledState = new ScheduledState();
        scheduledState.update(mission);
        missions.put(missionName, mission);
    }

    public void changeMissionStatus(String missionName, StatusMissionEnum status) {
        Mission mission = getMission(missionName);
        MissionState state = MissionStateFactory.create(status);
        state.update(mission);
    }

    public Mission getMission(String name) {
        return missions.get(name);
    }
    public Mission getMissionByRocketName(String rocketName) {
        return missions.values().stream()
                .filter(mission -> mission.getRockets().stream()
                        .anyMatch(rocket -> rocket.getName().equals(rocketName)))
                .findFirst().orElse(null);
    }

}
