package com.accesshq.model;

import com.accesshq.strategies.MatchingStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;

import static java.lang.Long.parseLong;

public class PlanetPage {

    private final WebDriver driver;

    public PlanetPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();
    }

    public void clickExplore(String planetName) {
        var planets = driver.findElements(By.className("planet"));

        for (var planet : planets) {
            if (planet.findElement(By.className("name")).getText().equalsIgnoreCase(planetName)) {
                planet.findElement(By.tagName("button")).click();
            }
        }

        new WebDriverWait(driver, Duration.ofSeconds(1)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
    }

    public String findFarthestPlanet() {
        var planets = driver.findElements(By.className("planet"));
        String farthestPlanet = "";
        long farthestDistance = 0;
        long distance = 0;

        for (WebElement planet : planets) {
            distance = parseLong(planet.findElement(By.className("distance")).getText().
                    replace(" km", "").replaceAll(",",""));
            if (distance > farthestDistance) {
                farthestDistance = distance;
                farthestPlanet = planet.findElement(By.className("name")).getText();
            }
        }

        return farthestPlanet;
    }

    public ArrayList<PlanetCard> getAllPlanets() {
        ArrayList<PlanetCard> result = new ArrayList<PlanetCard>();
        var planets = driver.findElements(By.className("planet"));

        for (var planet : planets) {
            result.add(new PlanetCard(planet));
        }
        return result;
    }

    public PlanetCard getFurthestPlanet() {
        long furthestDistance = 0;
        PlanetCard furthestPlanet = null;

        for (var planet : getAllPlanets()) {
            if (planet.getDistance() > furthestDistance) {
                furthestDistance = planet.getDistance();
                furthestPlanet = planet;
            }
        }
        return furthestPlanet;
    }

    public PlanetCard getPlanet(MatchingStrategy strategy) {

        for (var planet : getAllPlanets()) {
            if (strategy.match(planet)) {
                return planet;
            }
        }
        throw new NotFoundException("Planet not found");
    }
}
