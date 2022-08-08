public class TooLongTextAnalyzer implements TextAnalyzer {
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
