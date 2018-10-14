package com.blockchain.exercise.specs;

import com.blockchain.exercise.config.SpringConfig;
import com.blockchain.exercise.support.Environment;
import com.blockchain.exercise.support.ReportUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseSpec {

    @Autowired
    private WebDriver driver;

    @Autowired
    private ReportUtil reportUtil;

    @Autowired
    private Environment environment;

    @BeforeEach
    void beforeEach() {
        driver.get(environment.get("url"));
    }

    @AfterEach
    void afterEach() {
        reportUtil.takeScreenshot(driver);
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    void afterAll() {
        reportUtil.copyAllureProp();
    }
}
