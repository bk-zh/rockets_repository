package com.six.assignment.spacex.rocket.repository.domain.rocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocketServiceTest {
    private RocketService rocketService;

    @BeforeEach
    void setUp() {
        rocketService = new RocketService();
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


}