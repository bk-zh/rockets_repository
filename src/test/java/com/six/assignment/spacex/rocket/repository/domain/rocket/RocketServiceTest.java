package com.six.assignment.spacex.rocket.repository.domain.rocket;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RocketServiceTest {
    private RocketService rocketService;
    private MissionService missionService;

    @BeforeEach
    void setUp() {
        missionService = new MissionService();
        rocketService = new RocketService(missionService);
    }

    @Test
    void changeRocketStatus() {
        String rocketName = "rocket1";
        rocketService.addNewRocket(rocketName);

        Rocket rocket = rocketService.getRocket(rocketName);
        assertEquals(rocket.getStatus(), StatusRocketEnum.ON_GROUND);
        rocketService.changeRocketStatus(rocketName, StatusRocketEnum.IN_SPACE);
        assertEquals(rocket.getStatus(), StatusRocketEnum.IN_SPACE);

    }

    @Test
    void addNewRocket() {
        // given
        String rocketName = "rocket1";
        // when
        rocketService.addNewRocket(rocketName);
        // then
        Rocket rocket = rocketService.getRocket(rocketName);
        assertNotNull(rocket);
        assertEquals(StatusRocketEnum.ON_GROUND, rocket.getStatus());
    }


    @Test
    void forbidOverrideRocket() {
        // given
        String rocket1 = "rocket1";

        rocketService.addNewRocket(rocket1);

        // when & then
        IllegalArgumentException rocketDuplicationException = assertThrows(IllegalArgumentException.class, () -> {
            rocketService.addNewRocket("rocket1");
        });
        assertEquals("Rocket already exists", rocketDuplicationException.getMessage());

    }

    @Test
    void shouldUpdateMissionStatusWhenRocketInRepair() {
        // given
        rocketService.addNewRocket("rocket1");
        missionService.addNewMission("mission1");

        Rocket rocket = rocketService.getRocket("rocket1");
        Mission mission = missionService.getMission("mission1");
        mission.assignRocket(rocket);

        // when
        rocketService.changeRocketStatus("rocket1", StatusRocketEnum.IN_REPAIR);

        // then
        assertEquals(StatusRocketEnum.IN_REPAIR, rocket.getStatus());
        assertEquals(mission.getMissionStatus().getStatus(), StatusMissionEnum.PENDING);
    }

    @Test
    void shouldNotUpdateMissionStatusWhenRocketHasNoMission() {
        // given
        rocketService.addNewRocket("rocket1");
        missionService.addNewMission("mission1");

        Rocket rocket = rocketService.getRocket("rocket1");
        Mission mission = missionService.getMission("mission1");

        // when
        rocketService.changeRocketStatus("rocket1", StatusRocketEnum.IN_REPAIR);

        // then
        assertEquals(StatusRocketEnum.IN_REPAIR, rocket.getStatus());
        assertEquals(mission.getMissionStatus().getStatus(), StatusMissionEnum.SCHEDULED);
    }




}