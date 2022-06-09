package com.accesshq.strategies;

import com.accesshq.model.PlanetCard;

import java.util.Objects;

public class NameMatchingStrategy implements MatchingStrategy {

    private final String name;

    public NameMatchingStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean match(PlanetCard card) {
        return Objects.equals(card.getName(), name);
    }
}
