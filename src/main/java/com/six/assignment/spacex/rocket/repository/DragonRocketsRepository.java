package com.six.assignment.spacex.rocket.repository;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.assignment.AssignmentService;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketService;

import java.util.List;

public class DragonRocketsRepository {
    private final RocketService rocketService = new RocketService();
    private final MissionService missionService = new MissionService();
    private final AssignmentService assignmentService =
            new AssignmentService(rocketService, missionService);

    public List<Mission> getMissionsSummary() {
        throw new RuntimeException("not implemented yet");
    }


}