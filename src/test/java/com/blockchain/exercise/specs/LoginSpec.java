package com.blockchain.exercise.specs;

import com.blockchain.exercise.data.MemberType;
import com.blockchain.exercise.steps.LoginSteps;
import com.blockchain.exercise.steps.LogoutSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Feature("Login")
class LoginSpec extends BaseSpec {

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private LogoutSteps logOutSteps;

    @Test
    void memberLoginTest() {
        loginSteps
                .GivenILoginAS(MemberType.NORM)
                .ThenIShouldSeeTheMenu();
    }

    @Test
    void memberLogoutTest() {
        loginSteps
                .GivenILoginAS(MemberType.NORM)
                .ThenIShouldSeeTheMenu();
        logOutSteps
                .WhenILogOut()
                .ThenIAmLoggedOutOfTheSite();
    }

    @Test
    @Description("Intention of this test is to fail")
    void failureTest() {
        loginSteps
                .GivenILoginAS(MemberType.WRONGID)
                .ThenIShouldSeeTheMenu();
    }
}
