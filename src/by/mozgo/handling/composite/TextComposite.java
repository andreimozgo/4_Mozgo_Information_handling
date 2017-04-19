package by.mozgo.handling.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 19.04.2017.
 */
public class TextComposite implements TextComponent {
    private List<TextComponent> components = new ArrayList<>();

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
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
