package com.six.assignment.spacex.rocket.repository;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.assignment.AssignmentService;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketService;

import java.util.Comparator;
import java.util.List;

public class DragonRocketsRepository {
    private final MissionService missionService = new MissionService();
    private final RocketService rocketService = new RocketService(missionService);
    private final AssignmentService assignmentService =
            new AssignmentService(rocketService, missionService);

    public List<Mission> getMissionsSummary() {
        return missionService.getMissions().values().stream()
                .sorted(Comparator.comparingInt((Mission m) -> m.getRockets().size()).reversed()
                        .thenComparing(Mission::getName, Comparator.reverseOrder()))
                .toList();
    }


}