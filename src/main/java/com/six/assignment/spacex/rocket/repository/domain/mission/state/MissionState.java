package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;

public interface MissionState {
    void update(Mission mission);
    StatusMissionEnum getStatus();
    boolean canTransitionFrom(StatusMissionEnum target);
}