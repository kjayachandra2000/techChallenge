package com.blockchain.exercise.pages;

import com.blockchain.exercise.support.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BasePage extends PageActions {

    @Autowired
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
