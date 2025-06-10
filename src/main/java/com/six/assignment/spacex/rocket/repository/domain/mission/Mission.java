package com.six.assignment.spacex.rocket.repository.domain.mission;

import com.six.assignment.spacex.rocket.repository.domain.mission.state.MissionState;
import com.six.assignment.spacex.rocket.repository.domain.mission.state.ScheduledState;
import com.six.assignment.spacex.rocket.repository.domain.rocket.Rocket;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Mission {
    private final String name;
    @Setter
    private MissionState missionStatus;
    private final List<Rocket> rockets = new ArrayList<>();

    public void assignRocket(Rocket rocket) {
        rockets.add(rocket);
    }

    public void clearRockets() {
        rockets.clear();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " - " + missionStatus.getStatus().getPrintedValue() + " - Dragons: " + rockets.size());
        for (Rocket r : rockets) sb.append("\n  ").append(r);
        return sb.toString();
    }
}