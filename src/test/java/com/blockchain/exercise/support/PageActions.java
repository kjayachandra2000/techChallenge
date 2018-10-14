package com.blockchain.exercise.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageActions {

    protected WebDriver driver;

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public PageActions clickOn(WebElement element) {
        element.click();
        return this;
    }

    public PageActions typeInto(WebElement element, String string) {
        element.sendKeys(string);
        return this;
    }
}
