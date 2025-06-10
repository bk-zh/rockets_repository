package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;

import java.util.List;

public class PendingState implements MissionState {
    List<StatusMissionEnum> allowedTransitionsFrom = List.of(
            StatusMissionEnum.SCHEDULED,
            StatusMissionEnum.PENDING
    );
    @Override
    public void update(Mission mission) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public StatusMissionEnum getStatus() {
        return StatusMissionEnum.PENDING;
    }

    @Override
    public boolean canTransitionFrom(StatusMissionEnum target) {
        if (allowedTransitionsFrom.contains(target)) {
            return true;
        }
        throw new IllegalStateException("Mission status change not allowed from: " + target);
    }
}