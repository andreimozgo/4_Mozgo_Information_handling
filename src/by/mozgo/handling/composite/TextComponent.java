package by.mozgo.handling.composite;

import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class TextComponent {
    protected List<TextComponent> components;

    public List<TextComponent> getComponents() {
        return components;
    }

    public void setComponents(List<TextComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return components.toString();
    }
}
