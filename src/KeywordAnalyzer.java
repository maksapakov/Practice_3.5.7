public abstract class KeywordAnalyzer implements TextAnalyzer {

    protected abstract Label getLabel();

    protected abstract String[] getKeyword();

    public Label processText(String text) {
        for (String keyword :
                getKeyword()) {
            if (text.contains(keyword)) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}
