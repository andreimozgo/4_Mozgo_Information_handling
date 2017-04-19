package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;

import java.util.Comparator;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class ComponentsSizeComparator implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent component1, TextComponent component2) {
        return component1.getComponents().size() - component2.getComponents().size();
    }
}
