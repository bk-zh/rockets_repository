package com.six.assignment.spacex.rocket.repository.domain.assignment;

import com.six.assignment.spacex.rocket.repository.domain.assignment.AssignmentService;
import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        //Then
        Mission mission = missionService.getMission(missionName);
        assertNotNull(mission);
        assertNotNull(mission.getRockets());
        assertTrue(mission.getRockets().size()>0);
        Optional<Rocket> rocketOptional = mission.getRockets().stream()
                .filter(rocket -> rocket.getName().equals(rocketName))
                .findFirst();
        assertNotNull(rocketOptional.get());

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
        String rocketName = "rocket1";
        String missionName1 = "mission1";
        rocketService.addNewRocket(rocketName);
        missionService.addNewMission(missionName1);

        assignmentService.assignRocketToMission(rocketName, missionName1);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                assignmentService.assignRocketToMission(rocketName, missionName1));

        assertEquals("Rocket has already been assigned to a mission", ex.getMessage());
    }
}