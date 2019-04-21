package com.keepit.tests;

import com.keepit.businessflow.ConnectorsWorkflow;
import com.keepit.businessflow.BaseWorkflow;
import com.keepit.core.utils.Config;
import com.keepit.core.utils.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static com.keepit.core.utils.Config.getProperty;

public class BaseTest {
    protected Logger log = Logger.getLogger("WD: ");
    protected BaseWorkflow baseWorkflow;
    protected ConnectorsWorkflow connectorsWorkflow;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        log.info("Clean up logs");
        baseWorkflow = new BaseWorkflow();
        connectorsWorkflow = new ConnectorsWorkflow();
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({"email", "password"})
    public void beforeLog(String email, String password){
        loginWithCredentials(email, password);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        Test testAnnotation = method.getAnnotation(Test.class);
        if (testAnnotation != null) {
            log.info(method.getName() + " - " + method.getAnnotation(Test.class).description());
        }
    }

    protected void loginWithCredentials(String email, String password) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.manage().deleteAllCookies();
        driver.get(getProperty(Config.URL));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        baseWorkflow = new BaseWorkflow();
        baseWorkflow.login(email, password);
        Assert.assertTrue(baseWorkflow.userIsLogged());
    }

    @AfterSuite(alwaysRun = true)
    public static void afterClass() {
        DriverFactory.getInstance().removeDriver();
    }
}
