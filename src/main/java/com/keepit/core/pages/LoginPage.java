package com.keepit.core.pages;

import com.keepit.core.elements.base.Element;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='login-form-login']")
    private Element emailField;

    @FindBy(xpath = "//*[@id='login-form-password']")
    private Element passwordField;

    @FindBy(xpath = "//*[@id='login-form-login-button']")
    private Element btnSignIn;

    @FindBy(xpath = "//a[@class='forgot-password-link']")
    private Element btnForgotPass;


    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void submitPasswordBtnClick() {
        btnSignIn.click();
    }

}
