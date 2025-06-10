package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;

import java.util.List;

public class PendingState implements MissionState {
    List<StatusMissionEnum> allowedTransitionsFrom = List.of(
            StatusMissionEnum.SCHEDULED
    );

    @Override
    public void update(Mission mission) {
        canTransitionFrom(mission.getMissionStatus().getStatus());

        boolean hasRocketInRepair = mission.getRockets().stream()
                .anyMatch(rocket -> rocket.getStatus() == StatusRocketEnum.IN_REPAIR);

        if (!hasRocketInRepair || mission.getRockets().isEmpty()) {
            throw new IllegalStateException("Mission can transition to PENDING only if at least one rocket is in repair.");
        }

        mission.setMissionStatus(this);
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