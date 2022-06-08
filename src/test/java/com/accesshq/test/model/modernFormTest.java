package com.accesshq.test.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class modernFormTest {

    private final WebDriver driver;

    public modernFormTest(WebDriver driver) {
        this.driver = driver;
    }

    public void openForm() {
        driver.findElement(By.cssSelector("[aria-label='forms']")).click();
    }

    public void setName(String name) {
        driver.findElement(By.id("name")).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void clickAgree() {
        driver.findElement(By.cssSelector("[for='agree']")).click();
    }

    public void clickSubmit() {
        var buttons =  driver.findElements(By.cssSelector("button.v-btn"));
        for (var button : buttons) {
            if (button.getText().equalsIgnoreCase("submit")) {
                button.click();
                break;
            }
        }
    }

    public boolean checkToast() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("snackbar")));
        return driver.findElement(By.className("snackbar")).getText().equalsIgnoreCase("Thanks for your feedback Noah");
    }
}
