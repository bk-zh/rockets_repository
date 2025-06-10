package com.six.assignment.spacex.rocket.repository.domain.rocket;

import com.six.assignment.spacex.rocket.repository.domain.mission.Mission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Rocket {
    private final String name;
    private StatusRocketEnum status=StatusRocketEnum.ON_GROUND;
    private Mission mission;
}