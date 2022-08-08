public class SpamAnalyzer extends KeywordAnalyzer {
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

