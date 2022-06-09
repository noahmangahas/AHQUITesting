package com.accesshq.test;

import com.accesshq.model.PlanetPage;
import com.accesshq.strategies.DistanceMatchingStrategy;
import com.accesshq.strategies.NameMatchingStrategy;
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

        page.getPlanet(new NameMatchingStrategy("Earth")).clickExplore();
    }

    @Test
    public void furthestPlanetTest() throws Exception {
        PlanetPage page = new PlanetPage(driver);
        page.openPage();

        page.getFurthestPlanet().clickExplore();

        Assertions.assertEquals("Exploring Neptune", driver.findElement(By.className("popup-message")).getText());
    }

    @Test
    public void matchFurthestPlanet() {

        //Arrange
        PlanetPage page = new PlanetPage(driver);
        page.openPage();

        //Act
        page.getPlanet(new DistanceMatchingStrategy(4495000)).clickExplore();

        //Assert
        Assertions.assertEquals("Exploring Neptune", driver.findElement(By.className("popup-message")).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
