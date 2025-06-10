package com.six.assignment.spacex.rocket.repository.domain.rocket;

public interface RocketOperations {

    public void addNewRocket(String name);

    public void changeRocketStatus(String rocketName, StatusRocketEnum status);


}
