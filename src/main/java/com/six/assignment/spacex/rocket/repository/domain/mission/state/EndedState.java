package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;

import java.util.List;

public class EndedState implements MissionState {

    List<StatusMissionEnum> allowedTransitionsFrom = List.of(
            StatusMissionEnum.SCHEDULED,
            StatusMissionEnum.IN_PROGRESS,
            StatusMissionEnum.PENDING
    );

    @Override
    public void update(Mission mission) {
        canTransitionFrom(mission.getMissionStatus().getStatus());
        mission.clearRockets();
        mission.setMissionStatus(this);

    }

    @Override
    public StatusMissionEnum getStatus() {
        return StatusMissionEnum.ENDED;
    }

    @Override
    public boolean canTransitionFrom(StatusMissionEnum target) {
        if (allowedTransitionsFrom.contains(target)) {
            return true;
        }
        throw new IllegalStateException("Mission status change not allowed from: " + target);

    }
}