package com.blockchain.exercise.steps;

import com.blockchain.exercise.pages.DashBoardPage;
import com.blockchain.exercise.pages.LoginPage;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Component
public class LogoutSteps {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashBoardPage dashBoardPage;


    @Step
    public LogoutSteps WhenILogOut() {
        dashBoardPage
                .signOut();
        return this;
    }

    @Step
    public void ThenIAmLoggedOutOfTheSite() {
        assertThat("Login Page", loginPage.isDisplayed(), is(true));
    }
}
