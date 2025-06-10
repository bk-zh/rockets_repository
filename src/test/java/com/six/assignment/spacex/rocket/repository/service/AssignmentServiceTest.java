package com.six.assignment.spacex.rocket.repository.service;

import com.six.assignment.spacex.rocket.repository.domain.Mission;
import com.six.assignment.spacex.rocket.repository.domain.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.StatusMissionEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentServiceTest {
    private MissionService missionService;
    private RocketService rocketService;
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        missionService = new MissionService();
        rocketService = new RocketService();
        assignmentService=new AssignmentService(rocketService,missionService);
    }

    @Test
    void assignRocketsToMission() {
        //todo
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
        Rocket rocket = rocketService.getRocket(rocketName);
        assertNotNull(rocket);
        assertNotNull(rocket.getMission());
        assertEquals(rocket.getMission().getName(), missionName);

    }

    @Test
    void shouldThrowWhenAssigningToEndedMission() {
        String rocketName = "rocket1";
        String missionName1 = "mission1";
        String missionName2 = "mission2";
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
        String rocketName = "rocket1";
        String missionName1 = "mission1";
        String missionName2 = "mission2";
        rocketService.addNewRocket(rocketName);
        missionService.addNewMission(missionName1);
        missionService.addNewMission(missionName2);

        assignmentService.assignRocketToMission(rocketName, missionName1);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                assignmentService.assignRocketToMission(rocketName, missionName2));

        assertEquals("Rocket has already been assigned to a mission", ex.getMessage());
    }
}