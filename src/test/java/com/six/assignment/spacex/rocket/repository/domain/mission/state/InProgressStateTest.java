package com.six.assignment.spacex.rocket.repository.domain.mission.state;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class InProgressStateTest {

    @Test
    void shouldUpdateMissionToInProgressSuccessfully() {
        Mission mission = mock(Mission.class);
        Rocket rocket1 = mock(Rocket.class);
        Rocket rocket2 = mock(Rocket.class);

        when(mission.getMissionStatus()).thenReturn(new ScheduledState());
        when(mission.getRockets()).thenReturn(List.of(rocket1, rocket2));
        when(rocket1.getStatus()).thenReturn(StatusRocketEnum.ON_GROUND);
        when(rocket2.getStatus()).thenReturn(StatusRocketEnum.ON_GROUND);

        InProgressState inProgressState = new InProgressState();

        assertDoesNotThrow(() -> inProgressState.update(mission));

        verify(rocket1).setStatus(StatusRocketEnum.IN_SPACE);
        verify(rocket2).setStatus(StatusRocketEnum.IN_SPACE);
        verify(mission).setMissionStatus(inProgressState);
    }

    @Test
    void shouldFailUpdateWhenAnyRocketInRepair() {
        Mission mission = mock(Mission.class);
        Rocket rocket = mock(Rocket.class);

        when(mission.getMissionStatus()).thenReturn(new ScheduledState());
        when(mission.getRockets()).thenReturn(List.of(rocket));
        when(rocket.getStatus()).thenReturn(StatusRocketEnum.IN_REPAIR);

        InProgressState inProgressState = new InProgressState();

        assertThrows(IllegalStateException.class, () -> inProgressState.update(mission));
    }

    @Test
    void shouldFailUpdateWhenNoRocketsAssigned() {
        Mission mission = mock(Mission.class);

        when(mission.getMissionStatus()).thenReturn(new ScheduledState());
        when(mission.getRockets()).thenReturn(List.of());

        InProgressState inProgressState = new InProgressState();

        assertThrows(IllegalStateException.class, () -> inProgressState.update(mission));
    }

    @Test
    void shouldFailTransitionFromInvalidStatus() {
        Mission mission = mock(Mission.class);

        when(mission.getMissionStatus()).thenReturn(new EndedState());

        InProgressState inProgressState = new InProgressState();

        assertThrows(IllegalStateException.class, () -> inProgressState.update(mission));
    }
}