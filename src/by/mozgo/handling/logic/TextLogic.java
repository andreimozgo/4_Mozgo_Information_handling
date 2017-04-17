package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;

/**
 * Created by Андрей on 17.04.2017.
 */
public class TextLogic {
    public static String uniteText(TextComponent textComponent) {
        String text = new String();
        text = textComponent.toString();
        System.out.println(text);
        return text;
    }
}
