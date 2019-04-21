package com.keepit.helpers;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DataProv {

    
    @DataProvider(name = "SuitConnectorsTest")
    public static Object[][] ConnectorData() {return readData("src/test/resources/inputs/test.csv");}

    private static Object[][] readData(String path) {
        String[][] data = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            List<String[]> list = bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .collect(Collectors.toList());
            data = list.toArray(new String[list.size()][]);

        } catch (FileNotFoundException e) {
            //log.info("Oops, fle is not found");
        }
        return data;
    }

    private static Object[][] readListData(String path) {
        Object[][] data = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            data = new Object[][]{bufferedReader.lines().map(line -> Arrays.asList(line.split(";"))).toArray()};
        } catch (FileNotFoundException e) {
            //log.info("Oops, fle is not found");
        }
        return data;
    }

    private static Object[][] readDataAsMap(String path) {
        Object[][] data = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            data = new Object[][]{bufferedReader.lines()
                    .map(line -> {
                        Map<String, String> indicators = new HashMap<>();
                        for (String pair : line.split(";")) {
                            String[] keyAndValue = pair.split(":");
                            indicators.put(keyAndValue[0], keyAndValue[1]);
                        }
                        return indicators;
                    }).toArray()};
        } catch (FileNotFoundException e) {
            //log.info("Oops, fle is not found");
        }
        return data;
    }
}