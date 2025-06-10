package com.six.assignment.spacex.rocket.repository.domain.assignment;


import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;

import java.util.List;

public interface AssignmentOperations {

    public void assignRocketToMission(String rocketName, String missionName);

    public void assignRocketsToMission(List<Rocket> rockets, Mission mission) throws Exception;


}
