package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScheduledStateTest {

    @Test
    void shouldSetMissionStatusWhenCurrentStatusIsNull() {
        Mission mission = mock(Mission.class);

        when(mission.getMissionStatus()).thenReturn(null);
        when(mission.getRockets()).thenReturn(Collections.emptyList());
        ScheduledState scheduledState = new ScheduledState();

        assertDoesNotThrow(() -> scheduledState.update(mission));
        verify(mission).setMissionStatus(scheduledState);
    }

    @Test
    void shouldFailWhenCurrentStatusIsNotNull() {
        Mission mission = mock(Mission.class);
        MissionState missionState = mock(MissionState.class);

        when(mission.getMissionStatus()).thenReturn(missionState);
        when(missionState.getStatus()).thenReturn(StatusMissionEnum.IN_PROGRESS);

        ScheduledState scheduledState = new ScheduledState();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> scheduledState.update(mission)
        );

        assertEquals("Mission status change not allowed from: IN_PROGRESS", exception.getMessage());
    }

    @Test
    void shouldFailWhenCurrentStatusIsNullButHasRockets() {
        Mission mission = mock(Mission.class);
        Rocket rocket = mock(Rocket.class);

        when(mission.getMissionStatus()).thenReturn(null);
        when(mission.getRockets()).thenReturn(List.of(rocket));

        ScheduledState scheduledState = new ScheduledState();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> scheduledState.update(mission)
        );

        assertEquals("Mission status change not allowed to Scheduled if contains rockets", exception.getMessage());
    }

    @Test
    void canTransitionFromShouldAlwaysThrowException() {
        ScheduledState scheduledState = new ScheduledState();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> scheduledState.canTransitionFrom(StatusMissionEnum.PENDING)
        );

        assertEquals("Mission status change not allowed from: PENDING", exception.getMessage());
    }
}