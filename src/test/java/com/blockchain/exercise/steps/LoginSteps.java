package com.blockchain.exercise.steps;

import com.blockchain.exercise.data.MemberType;
import com.blockchain.exercise.pages.DashBoardPage;
import com.blockchain.exercise.pages.LoginPage;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Component
public class LoginSteps {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashBoardPage dashBoardPage;

    @Step("GivenIAmLoggedInAsA :: {memberType.walletID} / {memberType.password}")
    public LoginSteps GivenILoginAS(MemberType memberType) {
        loginPage
                .login(memberType);
        return this;
    }

    @Step
    public void ThenIShouldSeeTheMenu() {
        assertThat("Dashboard Page is displayed",
                dashBoardPage.isDisplayed(), is(true));
    }
}
