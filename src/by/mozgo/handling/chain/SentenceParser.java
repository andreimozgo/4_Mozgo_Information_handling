package by.mozgo.handling.chain;

import by.mozgo.handling.composite.Paragraph;
import by.mozgo.handling.composite.Sentence;
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

    @Override
    public TextComponent parseText(String text) {

        Matcher sentenceMatcher = Pattern.compile(SENTENCE_DELIMETER).matcher(text);
        List<TextComponent> sentences = new ArrayList<>();
        TextComponent textSentences = new Paragraph();
        while (sentenceMatcher.find()) {

            sentences.add(new Sentence(sentenceMatcher.group()));
        }


            textSentences.setComponents(sentences);
/*        for(String paragraph : paragraphs) {
            textParagraphs = nextParser.parseText(paragraph);
        }*/

            return textSentences;
        }
    }
