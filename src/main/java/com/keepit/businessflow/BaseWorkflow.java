package com.keepit.businessflow;

import com.keepit.core.pages.BasePage;
import com.keepit.core.pages.ConnectorsPage;
import com.keepit.core.pages.LoginPage;

public class BaseWorkflow {
    protected BasePage basePage;
    protected LoginPage loginPage;
    protected ConnectorsPage connectorsPage;

    public BaseWorkflow() {
        basePage = new BasePage();
        loginPage = new LoginPage();
        connectorsPage = new ConnectorsPage();
    }

    public void login(String email, String password) {
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.submitPasswordBtnClick();
    }

    public Boolean userIsLogged() {
        return loginPage.iconUserInfoIsEnabled();
    }

    public String getPageName()
    {
        return basePage.getPageName();
    }
}
