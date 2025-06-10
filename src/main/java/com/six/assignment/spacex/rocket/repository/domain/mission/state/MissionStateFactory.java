package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;

public class MissionStateFactory {
    public static MissionState create(StatusMissionEnum status) {
        return switch (status) {
            case SCHEDULED -> new ScheduledState();
            case PENDING -> new PendingState();
            case IN_PROGRESS -> new InProgressState();
            case ENDED -> new EndedState();
        };
    }
}