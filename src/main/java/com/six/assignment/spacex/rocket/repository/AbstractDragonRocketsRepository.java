package com.six.assignment.spacex.rocket.repository;

import com.six.assignment.spacex.rocket.repository.domain.assignment.AssignmentOperations;
import com.six.assignment.spacex.rocket.repository.domain.assignment.AssignmentService;
import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionOperations;
import com.six.assignment.spacex.rocket.repository.domain.mission.MissionService;
import com.six.assignment.spacex.rocket.repository.domain.mission.StatusMissionEnum;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketOperations;
import com.six.assignment.spacex.rocket.repository.domain.rocket.RocketService;
import com.six.assignment.spacex.rocket.repository.domain.rocket.StatusRocketEnum;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * DragonRocketsRepository facade - hiding composition complexity behind
 */
@RequiredArgsConstructor
abstract  class AbstractDragonRocketsRepository implements MissionOperations, RocketOperations, AssignmentOperations {
    protected final MissionService missionService = new MissionService();
    private final RocketService rocketService = new RocketService(missionService);
    private final AssignmentService assignmentService = new AssignmentService(rocketService, missionService);


    abstract List<Mission> getMissionsSummary();

    @Override
    public void assignRocketToMission(String rocketName, String missionName) {
        assignmentService.assignRocketToMission(rocketName, missionName);
    }

    @Override
    public void assignRocketsToMission(List<Rocket> rockets, Mission mission) throws Exception {
        assignmentService.assignRocketsToMission(rockets, mission);
    }

    @Override
    public void addNewMission(String missionName) {
        missionService.addNewMission(missionName);
    }

    @Override
    public void changeMissionStatus(String missionName, StatusMissionEnum status) {
        missionService.changeMissionStatus(missionName, status);
    }

    @Override
    public void addNewRocket(String name) {
        rocketService.addNewRocket(name);

    }

    @Override
    public void changeRocketStatus(String rocketName, StatusRocketEnum status) {
        rocketService.changeRocketStatus(rocketName, status);

    }


}