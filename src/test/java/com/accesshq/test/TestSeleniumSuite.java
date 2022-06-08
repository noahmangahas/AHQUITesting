package com.accesshq.test;

import com.accesshq.test.model.modernFormTest;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSeleniumSuite {
    WebDriver driver;

    @BeforeEach
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/");
    }

    @Test
    public void loginClickTest() {
        driver.findElement(By.id("loginButton")).click();
//        Assertions.assertEquals();
    }

    @Test
    public void submitFormTest() {
        modernFormTest form = new modernFormTest(driver);

        form.openForm();
        form.setName("Noah");
        form.setEmail("noah@email.com");
        form.clickAgree();

        form.clickSubmit();

        Assertions.assertTrue(form.checkToast());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
