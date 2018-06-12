package com.hkbea.odinfw.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

import java.util.Optional;

/**
 * Utilities for handling UI
 */
public class UiUtils {

    public static Optional<Component> findComponentById(String id) {
        return findComponentById(UI.getCurrent(), id);
    }

    /**
     *  Traverse the component tree to find the component by the given ID
     * @param root the root of the component tree
     * @param id the id of the component to find
     * @return the {@link Optional} instance for {@link Component}
     */
    public static Optional<Component> findComponentById(HasComponents root, String id) {
        for(Component child : root) {
            if(id.equals(child.getId())) {
                return Optional.of(child);
            } else if(child instanceof HasComponents) {
                Optional<Component> optionalComponent = findComponentById((HasComponents) child, id);

                if (!optionalComponent.isPresent()) {
                    continue;
                }

                return optionalComponent;
            }
        }

        return Optional.empty();
    }

    private UiUtils() {}
}
