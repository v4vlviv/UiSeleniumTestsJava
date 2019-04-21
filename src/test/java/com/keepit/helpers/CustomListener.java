package com.keepit.helpers;



import com.keepit.core.utils.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.util.FileCopyUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.io.IOException;

public class CustomListener extends HTMLReporter implements ITestListener {
    private final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    private Logger log = Logger.getLogger("WD");
    @Override
    public void onTestFailure(final ITestResult result)  {

        if (!result.isSuccess()) {
            try {
                System.setProperty(ESCAPE_PROPERTY, "false");
                String failureImageFileName = result.getName() + ".png";
                File scrFile = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
                FileCopyUtils.copy(scrFile, new File("target/test-output/html/"+failureImageFileName ));
                Reporter.setCurrentTestResult(result);
                Reporter.log("<a href=\""+ failureImageFileName + "\"> Click here to take a look at screenshot </a>");

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onTestSuccess(final ITestResult result) {
        log.info("Test SUCCESS: " + result.getName());
    }
    @Override
    public void onTestStart(final ITestResult result) {
        log.info("Test class started: " + result.getTestClass().getName());
        log.info("Test started: " + result.getName());
    }
    @Override
    public void onTestSkipped(final ITestResult result) {
        try {
            System.setProperty(ESCAPE_PROPERTY, "false");
            String failureImageFileName = result.getName() + ".png";
            File scrFile = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
            FileCopyUtils.copy(scrFile, new File("target/test-output/html/"+failureImageFileName ));
            Reporter.setCurrentTestResult(result);
            Reporter.log("<a href=\""+ failureImageFileName + "\"> Click here to take a look at screenshot </a>");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    }
    @Override
    public void onStart(final ITestContext context) {
    }
    @Override
    public void onFinish(final ITestContext context) {
    }

    private byte[] makeScreenshot() {
        return ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
