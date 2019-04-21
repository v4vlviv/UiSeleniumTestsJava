package com.keepit.tests;

import com.keepit.helpers.DataProv;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;



public class SuitConnectorsTest extends BaseTest {

    @Test(dataProviderClass = DataProv.class, dataProvider = "SuitConnectorsTest", priority=1)
    public void TestConnectorsList(String data) {
        Assert.assertTrue(connectorsWorkflow.compareListClServices(data));
    }

    @Test (priority=2)
    public void TestAddConnectorAdmin365() {
        Assert.assertEquals(connectorsWorkflow.addNewOfficeAdmin(), "©2019 Microsoft");
    }
}
