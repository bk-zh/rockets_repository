package com.six.assignment.spacex.rocket.repository.domain.assignment;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketService;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssignmentServiceTest {
    private MissionService missionService;
    private RocketService rocketService;
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        missionService = new MissionService();
        rocketService = new RocketService(missionService);
        assignmentService=new AssignmentService(rocketService,missionService);
    }

    @Test
    void assignRocketToMission() {
        // given
        String rocketName = "rocket1";
        String missionName = "mission1";
        rocketService.addNewRocket(rocketName);
        missionService.addNewMission(missionName);
        //when
        assignmentService.assignRocketToMission(rocketName,missionName);
        //Then
        Mission mission = missionService.getMission(missionName);
        assertNotNull(mission);
        assertNotNull(mission.getRockets());
        assertTrue(mission.getRockets().size()>0);
        Optional<Rocket> rocketOptional = mission.getRockets().stream()
                .filter(rocket -> rocket.getName().equals(rocketName))
                .findFirst();
        assertNotNull(rocketOptional.get());
        assertTrue(mission.getRockets().get(0).getStatus().equals(StatusRocketEnum.IN_SPACE));

    }

    @Test
    void shouldThrowWhenAssigningToEndedMission() {
        String rocketName = "rocket1";
        String missionName1 = "mission1";
        rocketService.addNewRocket(rocketName);
        missionService.addNewMission(missionName1);
        Mission oldMission = missionService.getMission(missionName1);
        missionService.changeMissionStatus(oldMission.getName(), StatusMissionEnum.ENDED);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                assignmentService.assignRocketToMission(rocketName, oldMission.getName()));

        assertEquals("Cannot assign to mission with status ended", ex.getMessage());


    }

    @Test
    void shouldThrowWhenRocketAlreadyAssigned() {
        String rocketName1 = "rocket1";
        String rocketName2 = "rocket2";
        String missionName1 = "mission1";
        String missionName2 = "mission2";
        rocketService.addNewRocket(rocketName1);
        rocketService.addNewRocket(rocketName2);
        missionService.addNewMission(missionName1);
        missionService.addNewMission(missionName2);

        assignmentService.assignRocketToMission(rocketName1, missionName1);
        assignmentService.assignRocketToMission(rocketName2, missionName2);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                assignmentService.assignRocketToMission(rocketName2, missionName1));

        assertEquals("Rocket has already been assigned to a mission", ex.getMessage());
    }

    @Test
    void shouldAssignRocketsToMissionSuccessfully() throws Exception {
        RocketService rocketService = mock(RocketService.class);
        MissionService missionService = mock(MissionService.class);

        Rocket rocket1 = new Rocket("rocket1");
        Rocket rocket2 = new Rocket("rocket2");
        Mission mission = new Mission("mission1");

        when(rocketService.getRocket("rocket1")).thenReturn(rocket1);
        when(rocketService.getRocket("rocket2")).thenReturn(rocket2);
        when(missionService.getMission("mission1")).thenReturn(mission);
        when(missionService.getMissions()).thenReturn(Map.of());

        AssignmentService assignmentService = new AssignmentService(rocketService, missionService);

        assignmentService.assignRocketsToMission(List.of(rocket1, rocket2), mission);

        assertEquals(2, mission.getRockets().size());
        assertTrue(mission.getRockets().containsAll(List.of(rocket1, rocket2)));
        assertEquals(StatusRocketEnum.IN_SPACE, rocket1.getStatus());
        assertEquals(StatusRocketEnum.IN_SPACE, rocket2.getStatus());
    }

    @Test
    void shouldFailAssigningAlreadyAssignedRocket() {
        RocketService rocketService = mock(RocketService.class);
        MissionService missionService = mock(MissionService.class);

        Rocket rocket = new Rocket("rocket1");
        Mission existingMission = new Mission("mission2");
        existingMission.assignRocket(rocket);

        Mission newMission = new Mission("mission1");

        when(rocketService.getRocket("rocket1")).thenReturn(rocket);
        when(missionService.getMission("mission1")).thenReturn(newMission);
        when(missionService.getMissions()).thenReturn(Map.of(existingMission.getName(), existingMission));

        AssignmentService assignmentService = new AssignmentService(rocketService, missionService);

        Exception exception = assertThrows(Exception.class, () ->
                assignmentService.assignRocketsToMission(List.of(rocket), newMission));

        assertTrue(exception.getMessage().contains("Rocket has already been assigned to a mission"));
    }

}