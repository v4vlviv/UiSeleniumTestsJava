package com.keepit.core.pages;

import com.keepit.core.elements.base.Element;
import com.keepit.core.pagefactory.ElementFactory;
import com.keepit.core.utils.CustomExpectedConditions;
import com.keepit.core.utils.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class BasePage {
    protected Logger log = Logger.getLogger("WD");
    protected Wait<WebDriver> wait;

    public BasePage() {
        ElementFactory.initElements(DriverFactory.getInstance().getDriver(), this);
    }

    @FindBy(xpath = "//h1[@class='content-page-title']")
    private Element pageName;

    // Header icons
    @FindBy(xpath = "//div[@class='messages-button']")
    private Element iconMessages;

    @FindBy(xpath = "//li[@class='right-dropdown-menu menu']")
    private Element iconMenu;

    @FindBy(xpath = "//li[@class='right-dropdown-menu settings']")
    private Element iconUserInfo;

    // Menu popup
    @FindBy(xpath = "//a[@rel='devices']")
    private Element iconConnectors;

    @FindBy(xpath = "//a[@rel='favourites']")
    private Element iconFavourites;

    @FindBy(xpath = "//a[@rel='links']")
    private Element iconLinks;

    @FindBy(xpath = "//a[@rel='support']")
    private Element iconSupport;

    @FindBy(xpath = "//a[@rel='adminpanel-audit-logs']")
    private Element iconAudit;

    @FindBy(xpath = "//a[@rel='users']")
    private Element iconUsers;

    @FindBy(xpath = "//a[@rel='sso']")
    private Element iconSso;

    @FindBy(xpath = "//a[@rel='connector-jobs']")
    private Element iconJobs;


    public Boolean iconUserInfoIsEnabled() {
        return iconUserInfo.isEnabled();
    }

    public void clickIconConnectors() {
        iconConnectors.click();
    }

    public void clickIconFavourites() {
        iconFavourites.click();
    }

    public void clickIconLinks() {
        iconLinks.click();
    }

    public void clickIconSupport() {
        iconSupport.click();
    }

    public void clickIconAudit() {
        iconAudit.click();
    }

    public void clickIconUsers() {
        iconUsers.click();
    }

    public void clickIconSso() {
        iconSso.click();
    }

    public void clickIconJobs() {
        iconJobs.click();
    }

    public String getPageName() {
        return pageName.getText();
    }

    public boolean waitForFileUpload() {
        wait = getWait(300);
        return wait.until(webDriver -> webDriver.manage().logs().get(LogType.BROWSER).filter(Level.INFO).stream().anyMatch(element -> element.getMessage().contains("Upload of Foundation_Other (1).zip finished")));
    }

    public boolean waitForControl(WebElement element) {
        log.info("Wait for get control of element");
        wait = getWait(100);
        return wait.until(webDriver -> element != null && element.isEnabled() && element.isDisplayed());
    }

    public boolean waitForControl(WebElement element, int seconds) {
        log.info("Wait for get control of element");
        wait = getWait(seconds);
        return wait.until(webDriver -> element != null && element.isEnabled() && element.isDisplayed());
    }

    public void waitElementToClick(WebElement webElement) {
        waitForControl(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForPageLoad() {
        new WebDriverWait(DriverFactory.getInstance().getDriver(), 80).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        log.info("Wait for page load");
    }

    private FluentWait getWait(int timeout) {
        return new FluentWait<>(DriverFactory.getInstance().getDriver())
                .withTimeout(java.time.Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    public void waitForAlert(int sec) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), sec, 100);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getPageTitle() {
        String title = DriverFactory.getInstance().getDriver().getTitle();
        log.info("Title of current page: " + title);
        return title;
    }

    public void timeWait(int timeout) {
        LocalDateTime endTime = LocalDateTime.now().plusSeconds(timeout);
        wait = getWait(timeout * 2);
        wait.until(webDriver -> LocalDateTime.now().isAfter(endTime));
    }

    public void waitForList(List<Element> list, int seconds) {
        wait = getWait(seconds);
        wait.until(CustomExpectedConditions.visibilityOfAllElements(list));
    }
}
