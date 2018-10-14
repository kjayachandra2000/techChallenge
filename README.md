# Automated Functional Tests for techChallenge

### Summary

This repository contains the tests for techChallenge web application, these tests are developed using Selenium , JUnit5 & Spring Dependency Injection

### Getting Started

Install Allure:

    > brew install allure

Run all tests:

    > mvn clean verify

Run a single feature:

    > mvn clean verify -Dtest=LoginSpec

Run a specific browser:

    > mvn clean verify -Dtest=LoginSpec -Dbrowser=chrome

To generate Allure HTML report:

    > allure generate -c -o output-folder

# Test Framework Code Guidelines

### Spec Classes

These classes are equivalent to [Cucumber](https://cucumber.io/docs/reference#feature) feature files. They should be easily readable and should just contain @Test methods. These @Test methods should only call steps and should not contain any logic or assertions.

Example:

```java
    @Test
    void memberLoginTest() {
        loginSteps
                .GivenILoginAS(MemberType.NORM)
                .WhenIAttemptToLogin()
                .ThenIShouldSeeTheMenu();
    }
```

Theses tests are written in the BDD format:
- Given = Preconditions for the test (Includes setting up mocks)
- When = Behaviours for the test
- Then = Assertions for the test (Only "Then"s should have assertions)

You can have multiples of each, however, "Given"s are always before all "When"s and "When"s are always before all "Then"s. A good rule of thumb is that all tests should never be more than five lines. If you find you have one longer than five lines, you might want to consider breaking it up into smaller tests.

In these Spec Classes, you should describe behavior and not user actions for example. Imagine you have the following test: "memberLogIn".

In this test to complete a login as a member, you have to fill in multiple fields and navigate through several screens. Given this you may be tempted to write the test in the following way:

```java
    @Test
    void memberLoginTest() {
        loginSteps
                .GivenIAmA(MemberType.NORM)
                .WhenIEnterUserName()
                .WhenIEnterPassword()
                .WhenILogin()
                .ThenIAmSuccessfullyLoggedIn();
    }
```

As you can see this way describes the user actions and has seven lines. User actions are more likely to change in the future than the user behaviour. That is to say, user’s will always login to the application, but their actions and the user interface flow they take to do it will most likely change. If this does happen the Spec Classes will have to be rewritten. The same test can be written as in the first example:

```java
    @Test
    void memberLoginTest() {
        loginSteps
                .GivenILoginAS(MemberType.NORM)
                .WhenIAttemptToLogin()
                .ThenIShouldSeeTheMenu();
    }
```

In this case, the user's behavior is described and the user actions are abstracted down into the Step Classes. Meaning if the actions change then only the steps need be updated and the user behaviour remains captured.

### Steps Classes

In these classes there should be minimal logic. Here we should only, save values between steps, call methods on Screen Classes, get values from Screen Class methods and assert on them. This is not the place for loops, if statements or finding elements on a page. In addition, steps should never call other steps.

Simple Logic Examples:
- Go to page X
- Remember Y
- Perform action on screen
- Assert Z is Y- etc..

Examples:

```java
    public LoginSteps GivenILoginAS(MemberType memberType) {
        loginPage
                .login(memberType);
        return this;
    }

    public void ThenIShouldSeeTheMenu() {
        assertThat("Dashboard Page is displayed",
                dashBoardPage.isDisplayed(), is(true));
    }
```

### Screen Classes

A Screen Class/Object is an object-oriented class that serves as an representation of a screen in your application. The test steps then use the methods of these screen classes whenever they need to interact with the UI of that screen. The benefit is that if the UI changes, the tests themselves don’t need to change, only the code within the Screen Class. Subsequently all changes to support the new UI are located in one place.

Screen classes should only do these things:
- Store element locators
- Actions (No output)
- Queries (With answers)
- Highly descriptive actions/queries (As directed by demands of Steps Classes)

**They should not include assertions.*

Example:

```java
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
```

Web elements in Screen Classes should be private. If a Steps Class needs to access it, it will need to do so via a public method. This stops the Steps Classes from being polluted with driver implementation details. To further limit this when naming methods in Screen Classes we should avoid terms like `click`, `tap`, `type`, `input`.

### Fluent Class Pattern

In addition we can make Screen and Step Class methods return themselves.
So instead of doing this:

```java
    @Test
    void failureTest() {
        loginSteps.GivenILoginAS(MemberType.WRONGID);
        loginSteps.ThenIShouldSeeTheMenu();
    }
```

We can do:

```java
    @Test
    void failureTest() {
        loginSteps
                .GivenILoginAS(MemberType.WRONGID)
                .ThenIShouldSeeTheMenu();
    }
```

****We should not make Screen Classes and Step Classes return Classes other than themselves as this can be hard to follow.***

### PageAction Class

Contains abstract methods, functions and actions, which can be utilised in any Screen Class. In addition, it is the home for driver helper methods such as “clickOn(WebElement element)” and “typeInto(WebElement element,String string)”. These allow us to have all the diver implementation in one place and to have clean readable code in our Screen Classes.

### BaseSpec

Contains the Test Hooks and is where the @Steps used in the Spec Classes are declared.

### Stable Tests

Non-Determinism in tests is worse than not having any tests at all. Before pushing any new tests run them five times in a row without a single fail.

### Code Quality

Code should be clean and kept to the same standard as production code, i.e. no lazy coding - make sure unused imports are removed, code is correctly aligned (IntelliJ - CMD+OPTION+L), with no random newlines and whitespaces. Where possible Java style guidelines should be followed.


### Wait Strategy

Sleeps are bad. Avoid "Thread.sleep(n);" and don't use "waitFor(element)" by default when finding elements. As these slow down the tests dramatically.