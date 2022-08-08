public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String[] keywords = {":(", "=(", ":|"};

    protected String[] getKeyword() {
        return keywords;
    }

    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
