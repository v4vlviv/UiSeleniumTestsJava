package com.keepit.businessflow;

import com.keepit.core.pages.ConnectorsPage;

import java.util.List;

public class ConnectorsWorkflow extends BaseWorkflow {

    public ConnectorsWorkflow() {
        connectorsPage = new ConnectorsPage();
    }

    public Boolean compareListClServices(String data){
        connectorsPage.clickBtnAddCloudServices();
        return connectorsPage.getListCloudServices().contains(data);
    }

    public String addNewOfficeAdmin(){
        connectorsPage.clickBtnAddCloudServices();
        connectorsPage.clickBtnOfficeAdmin();
        connectorsPage.setName("Test");
        connectorsPage.clickBtnNext();
        return connectorsPage.getSiteName();
    }

}
