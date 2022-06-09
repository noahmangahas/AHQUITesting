package com.accesshq.test;

import com.accesshq.model.PlanetPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Long.parseLong;


public class TestPlanetPage {

    WebDriver driver;

    @BeforeEach
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/");
    }

    @Test
    public void clickExploreTest() {
        PlanetPage page = new PlanetPage(driver);

        page.openPage();
        page.clickExplore("Earth");
    }

    @Test
    public void furthestPlanetTest() throws Exception {
        PlanetPage page = new PlanetPage(driver);
        page.openPage();

        page.clickExplore(page.findFarthestPlanet());

        Assertions.assertEquals("Exploring Neptune", driver.findElement(By.className("popup-message")).getText());
    }

    @Test
    public void matchFurthestPlanet() {

        //Arrange
        PlanetPage page = new PlanetPage(driver);
        page.openPage();

        //Act
        var planets = driver.findElements(By.className("planet"));
        String planetName = "";
        for (var planet : planets) {
            long distance = parseLong(planet.findElement(By.className("distance")).getText().
                    replace(" km", "").
                    replaceAll(",",""));
            if (distance == 4495000) {
                page.clickExplore(planet.findElement(By.className("name")).getText());
            }
        }

        //Assert
        Assertions.assertEquals("Exploring Neptune", driver.findElement(By.className("popup-message")).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
