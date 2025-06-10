package com.six.assignment.spacex.rocket.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
class Rocket {
    private final String name;
    private StatusRocketEnum status;
    private Mission mission;
}