public class Main {
    public static void main(String[] args) {
        // инициализация анализаторов для проверки в порядке данного набора анализаторов
        String[] spamKeywords = {"spam", "bad"};
        int commentMaxLength = 40;
        TextAnalyzer[] textAnalyzers1 = {
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers2 = {
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers3 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers4 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords)
        };
        TextAnalyzer[] textAnalyzers5 = {
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers6 = {
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords)
        };
        // тестовые комментарии
        String[] tests = new String[8];
        tests[0] = "This comment is so good.";                            // OK
        tests[1] = "This comment is so Loooooooooooooooooooooooooooong."; // TOO_LONG
        tests[2] = "Very negative comment !!!!=(!!!!;";                   // NEGATIVE_TEXT
        tests[3] = "Very BAAAAAAAAAAAAAAAAAAAAAAAAD comment with :|;";    // NEGATIVE_TEXT or TOO_LONG
        tests[4] = "This comment is so bad....";                          // SPAM
        tests[5] = "The comment is a spam, maybeeeeeeeeeeeeeeeeeeeeee!";  // SPAM or TOO_LONG
        tests[6] = "Negative bad :( spam.";                               // SPAM or NEGATIVE_TEXT
        tests[7] = "Very bad, very neg =(, very ..................";      // SPAM or NEGATIVE_TEXT or TOO_LONG
        TextAnalyzer[][] textAnalyzers = {textAnalyzers1, textAnalyzers2, textAnalyzers3,
                textAnalyzers4, textAnalyzers5, textAnalyzers6};
        //noinspection InstantiationOfUtilityClass
        Main testObject = new Main();
        int numberOfAnalyzer; // номер анализатора, указанный в идентификаторе textAnalyzers{№}
        int numberOfTest = 0; // номер теста, который соответствует индексу тестовых комментариев
        for (String test : tests) {
            numberOfAnalyzer = 1;
            System.out.print("test #" + numberOfTest + ": ");
            System.out.println(test);
            for (TextAnalyzer[] analyzers : textAnalyzers) {
                System.out.print(numberOfAnalyzer + ": ");
                //noinspection AccessStaticViaInstance
                System.out.println(testObject.checkLabels(analyzers, test));
                numberOfAnalyzer++;
            }
            numberOfTest++;
        }
        String[] badWords = {"спам", "отстой"};
        SpamAnalyzer spamA = new SpamAnalyzer(badWords);
        NegativeTextAnalyzer negativeTA = new NegativeTextAnalyzer();
        TooLongTextAnalyzer tooLongTA = new TooLongTextAnalyzer(40);
        TextAnalyzer[] analyzers = {spamA, negativeTA, tooLongTA};
        System.out.println(checkLabels(analyzers, "Хороший текст для теста."));
        System.out.println(checkLabels(analyzers, "Плохой текст для теста, так как это :( ужас."));
        System.out.println(checkLabels(analyzers, "Плохой текст для теста, полный отстой."));
        System.out.println(checkLabels(analyzers, "И это все, что вы хотели написать в комментариях. Да ну, вообще..."));


    }

public static Label checkLabels(TextAnalyzer[] textAnalyzer, String text) {
    TextAnalyzer[] textAnalyzers = textAnalyzer.clone();
    for (TextAnalyzer textAnalyzer1 :
            textAnalyzers) {
        if (textAnalyzer1.processText(text) != Label.OK) {
            return textAnalyzer1.processText(text);
        }
    }
    return Label.OK;
}

public static class SpamAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public SpamAnalyzer(String[] spamKeywords) {
        this.keywords = spamKeywords.clone();
    }


    public String[] getKeyword() {
        return keywords;
    }

    public Label getLabel() {
        return Label.SPAM;
    }

}

public static class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String[] keywords = {":(", "=(", ":|"};

    protected String[] getKeyword() {
        return keywords;
    }

    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}

public static class TooLongTextAnalyzer implements TextAnalyzer {
    private final int maxLength;

    public TooLongTextAnalyzer(int commentMaxLength) {
        this.maxLength = commentMaxLength;
    }

    private Label getLabel() {
        return Label.TOO_LONG;
    }

    @Override
    public Label processText(String text) {
        if (text.length() >= maxLength) {
            return getLabel();
        } else {
            return Label.OK;
        }
    }
}


}