package com.keepit.core.pagefactory;

import com.keepit.core.elements.base.Element;
import com.keepit.core.elements.base.ImplementedBy;

public final class ImplementedByProcessor {

    private ImplementedByProcessor() {
    }

    public static <T> Class<?> getWrapperClass(Class<T> iface) {
        if (iface.isAnnotationPresent(ImplementedBy.class)) {
            ImplementedBy annotation = iface.getAnnotation(ImplementedBy.class);
            Class<?> clazz = annotation.value();
            if (Element.class.isAssignableFrom(clazz)) {
                return annotation.value();
            }
        }
        throw new UnsupportedOperationException("Apply @ImplementedBy interface to your Interface " + iface.getCanonicalName() + " if you want to extend ");
    }

}
