package by.mozgo.handling.composite;

import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class TextComponent {
    List<TextComponent> components;



    public void setComponents(List<TextComponent> components) {
        this.components = components;
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return components.toString();
    }
}
