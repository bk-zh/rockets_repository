package com.six.assignment.spacex.rocket.repository.domain.rocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Rocket {
    private final String name;
    private StatusRocketEnum status=StatusRocketEnum.ON_GROUND;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("     " +name + " - " + status.getPrintedValue());
        return sb.toString();
    }
}