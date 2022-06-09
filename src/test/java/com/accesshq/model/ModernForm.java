package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ModernForm {

    private final WebDriver driver;

    public ModernForm(WebDriver driver) {
        this.driver = driver;
    }

    public void openModernForm() {
        driver.findElement(By.cssSelector("[aria-label='forms']")).click();
        var tabs = driver.findElements(By.cssSelector("[role=tab]"));
        for (var tab : tabs) {
            if (tab.getText().equalsIgnoreCase("modern")) {
                tab.click();
                break;
            }
        }
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

    public boolean isToast() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
        return driver.findElement(By.className("popup-message")).getText().equalsIgnoreCase("Thanks for your feedback Noah");
    }
}
