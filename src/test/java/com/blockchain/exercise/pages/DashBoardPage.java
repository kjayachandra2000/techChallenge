package com.blockchain.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class DashBoardPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'Sign out')]")
    private WebElement lnkSignOut;

    @FindBy(id = "whatsnew-icon")
    private WebElement lnkWhatsNew;

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(lnkWhatsNew);
    }

    public void signOut() {
        clickOn(lnkSignOut);
    }
}
