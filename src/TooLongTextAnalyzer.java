public class TooLongTextAnalyzer implements TextAnalyzer {
    public int maxLength;

    public TooLongTextAnalyzer(int commentMaxLength) {
        this.maxLength = commentMaxLength;
    }

    public Label getLabel() {
        return Label.TOO_LONG;
    }

    @Override
    public Label processText(String text) {
        if (text.length() >= maxLength) {
            return getLabel();
        }
        return Label.OK;
    }
}
