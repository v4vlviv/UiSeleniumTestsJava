package com.keepit.core.pages;

import com.keepit.core.elements.base.Element;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ConnectorsPage extends BasePage{
    public List<String> readListClServices;

    // Physical Connectors form
    @FindBy(xpath = "//a[@class='add-device-link download-client']")
    private Element btnDownload;

    // Cloud Services form
    @FindBy(xpath = "//a[@id='cloud-devices-button']")
    private Element btnAddCloudServices;

    // Online Drive form
    @FindBy(xpath = "//a[@id='sync-devices-button']")
    private Element btnAddDrive;

    // List Cloud Services
    @FindBy(xpath = "//div[contains(text(),'Add service')]/following-sibling::div")
    private List<Element> listCloudServices;

    // Add cloud services popup
    @FindBy(xpath = "//input[@id='name']")
    private Element txtName;

    @FindBy(xpath = "//*[@id='next-button-1']")
    private Element btnNext;

    @FindBy(xpath = "//*[@id='o365_admin']")
    private Element btnOfficeAdmin;

    @FindBy(xpath = "//*[@id='ftrCopy']")
    private Element pageName;

    public List<String> getListCloudServices() {
        readListClServices = new ArrayList<>();
        waitForList(listCloudServices, 3);
        for(int i = 0; i < listCloudServices.size(); i++) {
            readListClServices.add(listCloudServices.get(i).getText());
        }
        return readListClServices;
    }

    public String getSiteName() {
        return pageName.getText();
    }

    public void clickBtnAddCloudServices() {
        btnAddCloudServices.click();
    }

    public void clickBtnNext() {
        btnNext.click();
    }

    public void setName(String name) {
        txtName.sendKeys(name);
    }

    public void clickBtnOfficeAdmin() {
        btnOfficeAdmin.click();
    }



}
