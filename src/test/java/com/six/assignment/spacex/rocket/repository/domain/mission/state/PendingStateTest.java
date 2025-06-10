package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PendingStateTest {

    @Test
    void shouldSuccessfullyUpdateMissionToPendingWhenRocketInRepair() {
        Mission mission = mock(Mission.class);
        Rocket rocket = mock(Rocket.class);

        when(mission.getMissionStatus()).thenReturn(new ScheduledState());
        when(mission.getRockets()).thenReturn(List.of(rocket));
        when(rocket.getStatus()).thenReturn(StatusRocketEnum.IN_REPAIR);

        PendingState pendingState = new PendingState();

        assertDoesNotThrow(() -> pendingState.update(mission));
        verify(mission).setMissionStatus(pendingState);
    }

    @Test
    void shouldFailUpdateMissionToPendingWhenNoRocketInRepair() {
        Mission mission = mock(Mission.class);
        Rocket rocket = mock(Rocket.class);

        when(mission.getMissionStatus()).thenReturn(new ScheduledState());
        when(mission.getRockets()).thenReturn(List.of(rocket));
        when(rocket.getStatus()).thenReturn(StatusRocketEnum.ON_GROUND);

        PendingState pendingState = new PendingState();

        assertThrows(IllegalStateException.class, () -> pendingState.update(mission));
    }

    @Test
    void shouldFailUpdateMissionToPendingWhenNoRocketsAssigned() {
        Mission mission = mock(Mission.class);

        when(mission.getMissionStatus()).thenReturn(new ScheduledState());
        when(mission.getRockets()).thenReturn(List.of());

        PendingState pendingState = new PendingState();

        assertThrows(IllegalStateException.class, () -> pendingState.update(mission));
    }

    @Test
    void shouldFailTransitionFromInvalidStatus() {
        Mission mission = mock(Mission.class);

        when(mission.getMissionStatus()).thenReturn(new EndedState());

        PendingState pendingState = new PendingState();

        assertThrows(IllegalStateException.class, () -> pendingState.update(mission));
    }

    @Test
    void shouldSuccessTransitionFromInProgressStatus() {
        Mission mission = mock(Mission.class);

        when(mission.getMissionStatus()).thenReturn(new InProgressState());

        PendingState pendingState = new PendingState();

        assertThrows(IllegalStateException.class, () -> pendingState.update(mission));
    }
}