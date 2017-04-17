package by.mozgo.handling.chain;

import by.mozgo.handling.composite.TextComponent;

/**
 * @author Andrei Mozgo
 */
public interface TextParser {
    TextComponent parseText(String text);
}
