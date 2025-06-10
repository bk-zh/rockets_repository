package com.six.assignment.spacex.rocket.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        // given
        String rocket1 = "rocket1";
        String mission2 = "mission2";

        repository.addNewRocket(rocket1);
        repository.addNewMission(mission2);

        // when & then
        IllegalArgumentException rocketDuplicationException = assertThrows(IllegalArgumentException.class, () -> {
            repository.addNewRocket("rocket1");
        });
        assertEquals("Rocket already exists", rocketDuplicationException.getMessage());

        // when & then
        IllegalArgumentException missionDuplicationException = assertThrows(IllegalArgumentException.class, () -> {
            repository.addNewMission("mission2");
        });
        assertEquals("Mission already exists", missionDuplicationException.getMessage());

    }


    @Test
    void assignRocketToMissionTest() {
        // given
        String rocketName = "rocket1";
        String missionName = "mission1";
        repository.addNewRocket(rocketName);
        repository.addNewMission(missionName);
        //when
        repository.assignRocketToMission(rocketName,missionName);
        Rocket rocket = getRocketFromRepo(repository, rocketName);
        assertNotNull(rocket);
        assertNotNull(rocket.getMission());
        assertEquals(rocket.getMission().getName(), missionName);

    }
    @Test
    void shouldThrowWhenRocketAlreadyAssigned() {
        String rocketName = "rocket1";
        String missionName1 = "mission1";
        String missionName2 = "mission2";
        repository.addNewRocket(rocketName);
        repository.addNewMission(missionName1);
        repository.addNewMission(missionName2);

        repository.assignRocketToMission(rocketName, missionName1);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                repository.assignRocketToMission(rocketName, missionName2));

        assertEquals("Rocket has already been assigned to a mission", ex.getMessage());
    }

//    @Test
//    void shouldThrowWhenAssigningToEndedMission() {
//        String rocketName = "rocket1";
//        String missionName1 = "mission1";
//        String missionName2 = "mission2";
//        repository.addNewRocket(rocketName);
//        repository.addNewMission(missionName1);
//        Mission oldMission = getMissionFromRepo(repository,missionName1);
//        repository.changeMissionStatus(oldMission, StatusMissionEnum.ENDED);
//
//
//    }

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
