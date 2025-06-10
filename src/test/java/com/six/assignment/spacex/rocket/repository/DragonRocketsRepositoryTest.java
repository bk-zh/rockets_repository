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

        Mission transit = summary.get(0);
        assertEquals("Transit", transit.getName());
        assertEquals(StatusMissionEnum.IN_PROGRESS, transit.getMissionStatus().getStatus());
        assertEquals(3, transit.getRockets().size());
        assertEquals(StatusRocketEnum.IN_SPACE, getRocket(transit, "Red Dragon")); //<- error in provided solution answer
        assertEquals(StatusRocketEnum.IN_SPACE, getRocket(transit, "Dragon XL"));
        assertEquals(StatusRocketEnum.IN_SPACE, getRocket(transit, "Falcon Heavy"));

        Mission luna1 = summary.get(1);
        assertEquals("Luna1", luna1.getName());
        assertEquals(StatusMissionEnum.PENDING, luna1.getMissionStatus().getStatus());
        assertEquals(2, luna1.getRockets().size());
        assertEquals(StatusRocketEnum.IN_SPACE, getRocket(luna1, "Dragon 1"));
        assertEquals(StatusRocketEnum.IN_REPAIR, getRocket(luna1, "Dragon 2"));

        assertEquals("Vertical Landing", summary.get(2).getName());
        assertEquals(StatusMissionEnum.ENDED, summary.get(2).getMissionStatus().getStatus());
        assertEquals(0, summary.get(2).getRockets().size());

        assertEquals("Mars", summary.get(3).getName());
        assertEquals(StatusMissionEnum.SCHEDULED, summary.get(3).getMissionStatus().getStatus());
        assertEquals(0, summary.get(3).getRockets().size());

        assertEquals("Luna2", summary.get(4).getName());
        assertEquals(StatusMissionEnum.SCHEDULED, summary.get(4).getMissionStatus().getStatus());
        assertEquals(0, summary.get(4).getRockets().size());

        assertEquals("Double Landing", summary.get(5).getName());
        assertEquals(StatusMissionEnum.ENDED, summary.get(5).getMissionStatus().getStatus());
        assertEquals(0, summary.get(5).getRockets().size());
    }

    private StatusRocketEnum getRocket(Mission mission, String rocketName) {
        return mission.getRockets().stream()
                .filter(r -> r.getName().equals(rocketName))
                .findFirst()
                .orElseThrow().getStatus();
    }

}