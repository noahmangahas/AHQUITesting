package com.accesshq.strategies;

import com.accesshq.model.PlanetCard;

public interface MatchingStrategy {

    boolean match(PlanetCard card);
}
