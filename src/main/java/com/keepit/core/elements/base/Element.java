package com.keepit.core.elements.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

@ImplementedBy(ElementImpl.class)
public interface Element extends WebElement, WrapsElement, Locatable {

    default boolean elementWired(WebElement element) {
        return (element != null);
    }
    void clickJS();
    void moveToElement();
}
