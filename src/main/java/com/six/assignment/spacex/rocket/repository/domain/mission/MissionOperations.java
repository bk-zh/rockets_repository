package com.six.assignment.spacex.rocket.repository.domain.mission;

public interface MissionOperations {

    public void addNewMission(String missionName);

    public void changeMissionStatus(String missionName, StatusMissionEnum status);

}
