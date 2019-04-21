package com.keepit.core.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class DriverFactory {

    private Logger log = Logger.getLogger("WD");
    private WebDriver driver;
    private final String PATH_TO_DRIVERS_REPOSITORY = "/usr/lib/chromium-browser/";
   // private String browserName = System.getProperty("browserName");
   String browserName = "chrome";

    private DriverFactory() {
    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>() {

        @Override
        protected WebDriver initialValue() {
            DesiredCapabilities capabilities;
            if (driver == null) {
                switch (browserName) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", new File(PATH_TO_DRIVERS_REPOSITORY + "chromedriver").getPath());
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("test-type");
                        options.addArguments("--allow-running-insecure-content");
                        options.addArguments("enable-automation");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-infobars");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-browser-side-navigation");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");

                        driver = new ChromeDriver(options);
                        driver.manage().window().maximize();
                        log.info("Create instance of Chrome Driver");
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();    //TODO add driver for FF and capabilities
                        log.info("Create instance of FF Driver");
                        break;
                    default:
                        throw new IllegalArgumentException("Browser is not supported:" + browserName);
                }
            }
            return driver;
        }
    };

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    public void removeDriver() {
        driver = threadLocal.get();
        try {
            driver.manage().deleteAllCookies();
            driver.close();
            driver = null;
        } finally {
            threadLocal.remove();

        }
        closeDrivers();
        log.info("Driver is closed");
    }

    private void closeDrivers() {
        switch (browserName) {
            case "chrome":
                try {
                    Runtime.getRuntime().exec("taskkill /f /t /im " + "chromedriver").waitFor();
                } catch (IOException e) {
                    log.error("Failed to kill process: " + e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                log.info("Driver is closed");
        }
    }
}
