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
} 
