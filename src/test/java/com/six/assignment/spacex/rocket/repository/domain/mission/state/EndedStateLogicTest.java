package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndedStateLogicTest {

    private MissionService missionService;

    @BeforeEach
    void setUp() {
        missionService = new MissionService();
    }

    @Test
    void updateShouldClearRocketsIfTransitionIsAllowed() {
        missionService.addNewMission("mission1");
        Mission mission =missionService.getMission("mission1");

        missionService.changeMissionStatus("mission1", StatusMissionEnum.ENDED);
        assertTrue(mission.getRockets().isEmpty());
    }

    @Test
    void updateShouldThrowIfTransitionNotAllowed() {
        missionService.addNewMission("InvalidMission");
        missionService.changeMissionStatus("InvalidMission", StatusMissionEnum.ENDED);


        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            missionService.changeMissionStatus("InvalidMission", StatusMissionEnum.ENDED);
        });

        assertTrue(ex.getMessage().contains("Mission status change not allowed"));
    }

    @Test
    void canTransitionFromShouldAcceptAllowedStates() {
        EndedState endedState = new EndedState();

        assertTrue(endedState.canTransitionFrom(StatusMissionEnum.SCHEDULED));
        assertTrue(endedState.canTransitionFrom(StatusMissionEnum.PENDING));
        assertTrue(endedState.canTransitionFrom(StatusMissionEnum.IN_PROGRESS));
    }

    @Test
    void canTransitionFromShouldRejectDisallowedStates() {
        EndedState endedState = new EndedState();

        assertThrows(IllegalStateException.class, () ->
                endedState.canTransitionFrom(StatusMissionEnum.ENDED));
    }
}
