public abstract class KeywordAnalyzer implements TextAnalyzer {

    protected abstract Label getLabel();

    protected abstract String[] getKeyword();

    public Label processText(String text) {
        for (String keyword :
                getKeyword()) {
            if (text.regionMatches(true,
                    text.toLowerCase().indexOf(keyword),
                    keyword.toLowerCase(),
                    0,
                    keyword.length())) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}
