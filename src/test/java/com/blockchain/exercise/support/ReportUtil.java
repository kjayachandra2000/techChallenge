package com.blockchain.exercise.support;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ReportUtil {

    @Attachment
    public byte[] takeScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException ignore) {
            return null;
        }
    }

    public void copyAllureProp() {
        File BASE_DIR = new File(System.getProperty("user.dir"));
        Path copied = Paths.get(BASE_DIR + "/allure-results/environment.properties");
        Path originalPath = new File(BASE_DIR, "config.properties").toPath();
        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
