package com.keepit.core.pagefactory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ElementFactory {

    public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy) {
        T page = instantiatePage(driver, pageClassToProxy);
        return initElements(driver, page);
    }

    public static <T> T initElements(SearchContext searchContext, T page) {
        initElements(new CustomFieldDecorator(new DefaultElementLocatorFactory(searchContext)), page);
        return page;
    }

    public static void initElements(ElementLocatorFactory factory, Object page) {
        final ElementLocatorFactory factoryRef = factory;
        initElements(new CustomFieldDecorator(factoryRef), page);
    }

    public static void initElements(FieldDecorator decorator, Object page) {
        PageFactory.initElements(decorator, page);
    }


    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
