public class NegativeTextAnalyzer extends KeywordAnalyzer {
    public String[] keywords = {":(", "=(", ":|"};

    public String[] getKeyword() {
        return keywords;
    }

    public Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
