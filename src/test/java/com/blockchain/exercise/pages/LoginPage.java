package com.blockchain.exercise.pages;

import com.blockchain.exercise.data.MemberType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(name = "guid")
    private WebElement txtWalletID;

    @FindBy(name = "password")
    private WebElement txtPassword;

    @FindBy(css = "button[class*='LoginButton']")
    private WebElement btnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return btnLogin.isDisplayed();
    }

    public void login(MemberType memberType) {
        typeInto(txtWalletID, memberType.getWalletID())
                .typeInto(txtPassword, memberType.getPassword())
                .clickOn(btnLogin);
    }
}
