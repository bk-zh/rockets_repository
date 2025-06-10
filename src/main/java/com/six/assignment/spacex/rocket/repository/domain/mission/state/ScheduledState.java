package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;

public class ScheduledState implements MissionState {

    @Override
    public void update(Mission mission) {
        if (null == mission.getMissionStatus().getStatus()) {
            mission.setMissionStatus(this);
        } else {
            throw new IllegalStateException("Mission status change not allowed from: " + mission.getMissionStatus().getStatus());
        }
    }

    @Override
    public StatusMissionEnum getStatus() {
        return StatusMissionEnum.SCHEDULED;
    }

    @Override
    public boolean canTransitionFrom(StatusMissionEnum target) {
        throw new IllegalStateException("Mission status change not allowed from: " + target);
    }
}