package com.six.assignment.spacex.rocket.repository.domain.assignment;


import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.state.EndedState;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketService;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AssignmentService implements AssignmentOperations {
    private final RocketService rocketService;
    private final MissionService missionService;

    @Override
    public void assignRocketToMission(String rocketName, String missionName) {
        Rocket rocket = rocketService.getRocket(rocketName);
        if (null == rocket) {
            throw new IllegalArgumentException("Assignment failure, cannot find rocket: " + rocketName);
        }

        Mission mission = missionService.getMission(missionName);
        if (null == rocket) {
            throw new IllegalArgumentException("Assignment failure, cannot find mission: " + missionName);
        }

        if (isRocketAssigned(rocketName)) {
            throw new IllegalStateException("Rocket has already been assigned to a mission");
        }

        if (mission.getMissionStatus() instanceof EndedState)
            throw new IllegalStateException("Cannot assign to mission with status ended");

        mission.assignRocket(rocket);
        rocket.setStatus(StatusRocketEnum.IN_SPACE);
    }
    @Override
    public void assignRocketsToMission(List<Rocket> rockets, Mission mission) throws Exception {
        for (Rocket rocket : rockets) {
            assignRocketToMission(rocket.getName(), mission.getName());
        }

    }

    private boolean isRocketAssigned(String rocketName) {
        return missionService.getMissions().values().stream()
                .anyMatch(mission -> mission.getRockets()
                        .stream()
                        .anyMatch(r -> r.getName().equals(rocketName)));
    }
}
