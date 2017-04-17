package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;

import java.util.Comparator;

/**
 * Created by Андрей on 17.04.2017.
 */
public class ComponentsSizeComporator implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent component1, TextComponent component2) {
        return component1.getComponents().size() - component2.getComponents().size();
    }
}
