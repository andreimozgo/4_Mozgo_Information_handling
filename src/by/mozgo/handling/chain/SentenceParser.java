package by.mozgo.handling.chain;

import by.mozgo.handling.composite.Paragraph;
import by.mozgo.handling.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrei Mozgo
 */
public class SentenceParser implements TextParser {
    private final String SENTENCE_DELIMETER = "(?s)[\\p{Upper}+\\-(](.(?!\\.))*..";
    private TextParser nextParser = new LexemeParser();

    @Override
    public TextComponent parseText(String text) {
        Matcher sentenceMatcher = Pattern.compile(SENTENCE_DELIMETER).matcher(text);
        List<TextComponent> sentences = new ArrayList<>();
        TextComponent textSentences = new Paragraph();

        while (sentenceMatcher.find()) {
            TextComponent sentence = nextParser.parseText(sentenceMatcher.group());
            sentences.add(sentence);
        }

        textSentences.setComponents(sentences);
            return textSentences;
        }
    }
