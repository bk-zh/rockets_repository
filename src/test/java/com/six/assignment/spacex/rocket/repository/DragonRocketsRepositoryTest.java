package com.six.assignment.spacex.rocket.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DragonRocketsRepositoryTest {

    private DragonRocketsRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DragonRocketsRepository();
    }

    @Test
    void addNewRocketTest() {
        // given
        String rocketName = "rocket1";
        // when
        repository.addNewRocket(rocketName);
        // then
        Rocket rocket = getRocketFromRepo(repository, rocketName);
        assertNotNull(rocket);
        assertEquals(StatusRocketEnum.ON_GROUND, rocket.getStatus());

    }

    @Test
    void addNewMissionTest() {
        // given
        String missionName = "mission1";
        // when
        repository.addNewMission(missionName);
        // then
        Mission mission = getMissionFromRepo(repository, missionName);
        assertNotNull(mission);
        assertEquals(StatusMissionEnum.SCHEDULED, mission.getStatus());

    }
    @Test
    void forbidOverrideMissionAndRocket() {
        //todo
    }


    @Test
    void assignRocketToMissionTest() {
        // given
        String rocketName = "rocket1";
        String missionName = "mission1";
        repository.addNewRocket(rocketName);
        repository.addNewMission(missionName);
        repository.assignRocketToMission(rocketName,missionName);
        Rocket rocket = getRocketFromRepo(repository, rocketName);
        assertNotNull(rocket);
        assertNotNull(rocket.getMission());
        assertEquals(rocket.getMission().getName(), missionName);

    }



    //supporting method for private accessors  rockets/missions in  DragonRocketsRepository
    private Rocket getRocketFromRepo(DragonRocketsRepository repo, String name) {
        try {
            var field = DragonRocketsRepository.class.getDeclaredField("rockets");
            field.setAccessible(true);
            var map = (Map<String, Rocket>) field.get(repo);
            return map.get(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //supporting method for private accessors  rockets/missions in  DragonRocketsRepository
    private Mission getMissionFromRepo(DragonRocketsRepository repo, String name) {
        try {
            var field = DragonRocketsRepository.class.getDeclaredField("missions");
            field.setAccessible(true);
            var map = (Map<String, Mission>) field.get(repo);
            return map.get(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} 
