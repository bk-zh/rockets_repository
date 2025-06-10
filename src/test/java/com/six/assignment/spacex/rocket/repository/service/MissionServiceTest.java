package com.six.assignment.spacex.rocket.repository.service;

import com.six.assignment.spacex.rocket.repository.domain.Mission;
import com.six.assignment.spacex.rocket.repository.domain.StatusMissionEnum;
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
        assertEquals(StatusMissionEnum.SCHEDULED, mission.getStatus());

    }

    @Test
    void changeMissionStatus() {
        String missionName = "rocket1";
        missionService.addNewMission(missionName);

        Mission mission = missionService.getMission(missionName);
        assertEquals(mission.getStatus(), StatusMissionEnum.SCHEDULED);
        missionService.changeMissionStatus(mission.getName(), StatusMissionEnum.IN_PROGRESS);
        assertEquals(mission.getStatus(), StatusMissionEnum.IN_PROGRESS);

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