package by.mozgo.handling.composite;

import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class TextComponent {
    protected List<TextComponent> components;
    private ComponentLevel level;

    public List<TextComponent> getComponents() {
        return components;
    }

    public void setComponents(List<TextComponent> components) {
        this.components = components;
    }

    public void setLevel(ComponentLevel level) {
        this.level = level;
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
