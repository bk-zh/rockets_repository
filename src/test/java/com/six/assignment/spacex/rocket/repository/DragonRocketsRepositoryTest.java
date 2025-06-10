package com.six.assignment.spacex.rocket.repository;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DragonRocketsRepositoryTest {


    private DragonRocketsRepository dragonRocketsRepository;

    @BeforeEach
    void setUp() {
        dragonRocketsRepository = new DragonRocketsRepository();

        //missions
        String mars = "Mars";
        String luna1 = "Luna1";
        String doubleLending = "Double Landing";
        String transit = "Transit";
        String luna2 = "Luna2";
        String verticalLending = "Vertical Landing";

        //rockets
        String dragon1 = "Dragon 1";
        String dragon2 = "Dragon 2";
        String dragon3 = "Dragon 3";
        String redDragon = "Red Dragon";
        String dragonXL = "Dragon XL";
        String falconHeavy = "Falcon Heavy";


        dragonRocketsRepository.addNewRocket(dragon1);
        dragonRocketsRepository.addNewRocket(dragon2);
        dragonRocketsRepository.addNewRocket(dragon3);
        dragonRocketsRepository.addNewRocket(redDragon);
        dragonRocketsRepository.addNewRocket(dragonXL);
        dragonRocketsRepository.addNewRocket(falconHeavy);


        dragonRocketsRepository.addNewMission(mars);
        dragonRocketsRepository.addNewMission(luna1);
        dragonRocketsRepository.addNewMission(doubleLending);
        dragonRocketsRepository.addNewMission(transit);
        dragonRocketsRepository.addNewMission(luna2);
        dragonRocketsRepository.addNewMission(verticalLending);

        dragonRocketsRepository.assignRocketToMission(dragon1, luna1);
        dragonRocketsRepository.assignRocketToMission(dragon2, luna1);
        dragonRocketsRepository.assignRocketToMission(redDragon, transit);
        dragonRocketsRepository.assignRocketToMission(dragonXL, transit);
        dragonRocketsRepository.assignRocketToMission(falconHeavy, transit);

        dragonRocketsRepository.changeRocketStatus(dragon1, StatusRocketEnum.IN_SPACE);
        dragonRocketsRepository.changeRocketStatus(dragon2, StatusRocketEnum.IN_REPAIR);
        dragonRocketsRepository.changeRocketStatus(dragonXL, StatusRocketEnum.IN_SPACE);
        dragonRocketsRepository.changeRocketStatus(falconHeavy, StatusRocketEnum.IN_SPACE);


        dragonRocketsRepository.changeMissionStatus(doubleLending, StatusMissionEnum.ENDED);
        dragonRocketsRepository.changeMissionStatus(transit, StatusMissionEnum.IN_PROGRESS);
        dragonRocketsRepository.changeMissionStatus(verticalLending, StatusMissionEnum.ENDED);


    }

    @Test
    void shouldReturnCorrectMissionSummaryOrder() {
        List<Mission> summary = dragonRocketsRepository.getMissionsSummary();

        assertEquals(6, summary.size());
        assertEquals("Transit", summary.get(0).getName()); // 3 rockets
        assertEquals("Luna1", summary.get(1).getName());   // 2 rockets
        assertEquals("Vertical Landing", summary.get(2).getName()); // 0 rockets, desc. alphabetical
        assertEquals("Mars", summary.get(3).getName());
        assertEquals("Luna2", summary.get(4).getName());
        assertEquals("Double Landing", summary.get(5).getName());
    }
}
