package by.mozgo.handling.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class TextComponent {
    protected List<TextComponent> components = new ArrayList<>();

    public void add(TextComponent component) {
        components.add(component);
    }

    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    public List<TextComponent> getComponents() {
        return components;
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : components) {
            stringBuilder.append(component.toString());
        }
        return stringBuilder.toString();
    }
}
