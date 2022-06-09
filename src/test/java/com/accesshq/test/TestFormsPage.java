package com.accesshq.test;

import com.accesshq.model.ModernForm;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestFormsPage {
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
    }

    @Test
    public void submitFormTest() {
        ModernForm form = new ModernForm(driver);

        form.openModernForm();
        form.setName("Noah");
        form.setEmail("noah@email.com");
        form.clickAgree();

        form.clickSubmit();

        Assertions.assertTrue(form.isToast());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
