package com.six.assignment.spacex.rocket.repository.domain.mission;

import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissionServiceTest {
    private MissionService missionService;


    @BeforeEach
    void setUp() {
        missionService = new MissionService();
    }

    @Test
    void addNewMission() {
        // given
        String missionName = "mission1";
        // when
        missionService.addNewMission(missionName);
        // then
        Mission mission = missionService.getMission(missionName);
        assertNotNull(mission);
        assertEquals(StatusMissionEnum.SCHEDULED, mission.getMissionStatus().getStatus());

    }

    @Test
    void changeMissionStatus() {
        String missionName = "rocket1";
        missionService.addNewMission(missionName);

        Mission mission = missionService.getMission(missionName);
        assertEquals(mission.getMissionStatus().getStatus(), StatusMissionEnum.SCHEDULED);
        missionService.changeMissionStatus(mission.getName(), StatusMissionEnum.ENDED);
        assertEquals(mission.getMissionStatus().getStatus(), StatusMissionEnum.ENDED);

    }
    @Test
    void shouldReturnMissionForGivenRocketName() {

        missionService.addNewMission("mission1");
        missionService.addNewMission("mission2");
        Mission mission1 = missionService.getMission("mission1");
        Mission mission2 = missionService.getMission("mission2");

        Rocket rocket1 = new Rocket("rocket1");
        mission1.assignRocket(rocket1);

        Rocket rocket2 = new Rocket("rocket2");
        mission2.assignRocket(rocket2);

        Mission result = missionService.getMissionByRocketName("rocket2");

        assertNotNull(result);
        assertEquals(result.getName(), "mission2");
    }

    @Test
    void shouldReturnEmptyWhenRocketNameNotFound() {
        missionService.addNewMission("mission1");
        missionService.addNewMission("mission2");
        Mission mission1 = missionService.getMission("mission1");
        Mission mission2 = missionService.getMission("mission2");

        Rocket rocket1 = new Rocket("rocket1");
        mission1.assignRocket(rocket1);

        Rocket rocket2 = new Rocket("rocket2");
        mission2.assignRocket(rocket2);

        Mission result = missionService.getMissionByRocketName("rocket3");

        assertNull(result);
    }

    @Test
    void forbidOverrideMission() {
        // given
        String mission2 = "mission2";
        missionService.addNewMission(mission2);

        // when & then
        IllegalArgumentException missionDuplicationException = assertThrows(IllegalArgumentException.class, () -> {
            missionService.addNewMission("mission2");
        });
        assertEquals("Mission already exists", missionDuplicationException.getMessage());

    }
}