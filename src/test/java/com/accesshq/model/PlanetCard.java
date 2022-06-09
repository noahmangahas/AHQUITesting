package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PlanetCard {

    private final WebElement card;

    public PlanetCard(WebElement card) {
        this.card = card;
    }

    public String getName() {
        return card.findElement(By.className("name")).getText();
    }

    public long getDistance() {
        return Long.parseLong(card.findElement(By.className("distance")).getText().
                replace(" km", "").
                replaceAll(",",""));
    }

    public void clickExplore() {
        card.findElement(By.tagName("button")).click();
    }
}
