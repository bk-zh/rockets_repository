package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;

import java.util.List;

public class InProgressState implements MissionState {
    List<StatusMissionEnum> allowedTransitionsFrom = List.of(
            StatusMissionEnum.SCHEDULED,
            StatusMissionEnum.PENDING
    );
    @Override
    public void update(Mission mission) {
        canTransitionFrom(mission.getMissionStatus().getStatus());

        boolean anyInRepair = mission.getRockets().stream()
                .anyMatch(rocket -> rocket.getStatus() == StatusRocketEnum.IN_REPAIR);

        if (anyInRepair || mission.getRockets().isEmpty()) {
            throw new IllegalStateException("Mission cannot transition to IN_PROGRESS if any rocket is in repair or no rockets assigned");
        }

        mission.getRockets().forEach(rocket -> rocket.setStatus(StatusRocketEnum.IN_SPACE));
        mission.setMissionStatus(this);
    }

    @Override
    public StatusMissionEnum getStatus() {
        return StatusMissionEnum.IN_PROGRESS;
    }

    @Override
    public boolean canTransitionFrom(StatusMissionEnum target) {
        if (allowedTransitionsFrom.contains(target)) {
            return true;
        }
        throw new IllegalStateException("Mission status change not allowed from: " + target);
    }
}