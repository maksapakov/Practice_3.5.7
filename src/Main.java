import java.util.Arrays;

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
        Main testObject = new Main();
        int numberOfAnalyzer; // номер анализатора, указанный в идентификаторе textAnalyzers{№}
        int numberOfTest = 0; // номер теста, который соответствует индексу тестовых комментариев
        for (String test : tests) {
            numberOfAnalyzer = 1;
            System.out.print("test #" + numberOfTest + ": ");
            System.out.println(test);
            for (TextAnalyzer[] analyzers : textAnalyzers) {
                System.out.print(numberOfAnalyzer + ": ");
                System.out.println(testObject.checkLabels(analyzers, test));
                numberOfAnalyzer++;
            }
            numberOfTest++;
        }
        SpamAnalyzer spamAnalyzer = new SpamAnalyzer(spamKeywords);
        System.out.println(Arrays.toString(spamAnalyzer.getKeyword()));
        System.out.println(spamAnalyzer.processText(tests[5]) + " test");
//        System.out.println(tests[5].regionMatches(true,17,"spam",0,4) + " : " + tests[5]);


    }
    public Label checkLabels(TextAnalyzer[] textAnalyzer, String text) {

        return Label.OK;
    }

    public static class SpamAnalyzer implements TextAnalyzer {
        private String[] keywords;

        public SpamAnalyzer(String[] spamKeywords) {
            this.keywords = spamKeywords.clone();
        }

        private String[] getKeyword() {
            return keywords;
        }

        private Label getLabel() {
            return Label.SPAM;
        }

        @Override
        public Label processText(String text) {

            Label label = getLabel();

            for (String keyword:
                    keywords) {
/*
                if (text.regionMatches(true,0,keyword,0,keyword.length()) == false) {
                    label = Label.OK;
                } else {
                    getLabel();
                }
*/
               label = text.regionMatches(true, 0, keyword, 0, keyword.length()) ?
                       getLabel() : Label.OK;
//                System.out.println(text.regionMatches(true,0,keyword,0,keyword.length()) + " : " + keyword + " : " + text);
            }
            return label;
        }
    }

    public static class NegativeTextAnalyzer implements TextAnalyzer {

        @Override
        public Label processText(String text) {
            return null;
        }
    }

    public static class TooLongTextAnalyzer implements TextAnalyzer {
        public TooLongTextAnalyzer(int commentMaxLength) {
        }

        @Override
        public Label processText(String text) {
            return null;
        }
    }

    public abstract class KeywordAnalyzer implements TextAnalyzer{

        private String str;

        protected abstract Label getLabel();
        protected abstract String[] getKeyword();

        public Label processText(String text) {

            this.str = text;


            return null;
        }

    }
}
