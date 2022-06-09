package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
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
}
