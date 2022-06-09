package com.accesshq.strategies;

import com.accesshq.model.PlanetCard;

public class DistanceMatchingStrategy implements MatchingStrategy{

    private long distance;

    public DistanceMatchingStrategy(long distance) {
        this.distance = distance;
    }

    @Override
    public boolean match(PlanetCard card) {
        return card.getDistance() == distance;
    }
}
