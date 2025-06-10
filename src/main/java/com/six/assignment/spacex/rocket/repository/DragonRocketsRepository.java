package com.six.assignment.spacex.rocket.repository;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;

import java.util.Comparator;
import java.util.List;

public class DragonRocketsRepository extends AbstractDragonRocketsRepository {

    public List<Mission> getMissionsSummary() {
        return missionService.getMissions().values().stream()
                .sorted(Comparator.comparingInt((Mission m) -> m.getRockets().size()).reversed()
                        .thenComparing(Mission::getName, Comparator.reverseOrder()))
                .toList();
    }

}