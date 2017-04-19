package by.mozgo.handling.composite;

import java.util.List;

/**
 * Created by Andrei Mozgo. 2017.
 */
public interface TextComponent {
    default void add(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    default void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    default List<TextComponent> getComponents() {
        throw new UnsupportedOperationException();
    }
}
