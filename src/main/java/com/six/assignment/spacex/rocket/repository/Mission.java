package com.six.assignment.spacex.rocket.repository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
class Mission {
    private final String name;
    @Setter
    private StatusMissionEnum status;
    private final List<Rocket> rockets = new ArrayList<>();


}