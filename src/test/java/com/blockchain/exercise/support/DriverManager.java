package com.blockchain.exercise.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DriverManager {

    @Autowired
    private Environment environment;

    private static final int implicitWait = 20;

    @Bean
    public WebDriver getDriver() {
        WebDriver driver;
        switch (environment.get("browser").toLowerCase()) {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            default:
                driver = getChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("disable-extensions");
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }

    private WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        return new FirefoxDriver();
    }
}