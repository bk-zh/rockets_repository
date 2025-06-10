package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;

public class EndedState implements MissionState {
    @Override
    public void update(Mission mission) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public StatusMissionEnum getStatus() {
        return StatusMissionEnum.ENDED;
    }
}