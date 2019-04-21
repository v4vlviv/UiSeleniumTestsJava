package com.keepit.core.utils;


import com.keepit.core.elements.base.Element;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Iterator;
import java.util.List;

public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> textContentEquals(Element element, String expected) {
        return webDriver -> element.getText().equals(expected);
    }

    public static ExpectedCondition<List<Element>> visibilityOfAllElements(final List<Element> elements) {
        return new ExpectedCondition<List<Element>>() {
            public List<Element> apply(WebDriver driver) {
                Iterator var2 = elements.iterator();

                Element element;
                do {
                    if (!var2.hasNext()) {
                        return elements.size() > 0 ? elements : null;
                    }

                    element = (Element) var2.next();
                } while (element.isDisplayed());

                return null;
            }

            public String toString() {
                return "visibility of all " + elements;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfAllElements(final List<Element> elements) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return elements.stream().allMatch((x$0) -> {
                    return isInvisible(x$0);
                });
            }

            public String toString() {
                return "invisibility of all elements " + elements;
            }
        };
    }

    private static boolean isInvisible(Element element) {
        try {
            return !element.isDisplayed();
        } catch (StaleElementReferenceException var2) {
            return true;
        }
    }
}
