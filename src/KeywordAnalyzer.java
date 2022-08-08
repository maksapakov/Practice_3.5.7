public abstract class KeywordAnalyzer implements TextAnalyzer {

    protected abstract Label getLabel();

    protected abstract String[] getKeyword();

    public Label processText(String text) {
        Label label = getLabel();
        for (String keyword :
                getKeyword()) {
            if (!text.regionMatches(true,
                    text.toLowerCase().indexOf(keyword),
                    keyword.toLowerCase(),
                    0,
                    keyword.length())) {
                label = Label.OK;
            } else {
                label = getLabel();
                break;
            }
        }
        return label;
    }
}
