package com.six.assignment.spacex.rocket.repository;

import com.six.assignment.spacex.rocket.repository.domain.Mission;
import com.six.assignment.spacex.rocket.repository.service.AssignmentService;
import com.six.assignment.spacex.rocket.repository.service.MissionService;
import com.six.assignment.spacex.rocket.repository.service.RocketService;

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