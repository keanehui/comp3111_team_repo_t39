package comp3111.popnames;

import javafx.scene.control.TextField;

/**
 * Helper class for validating TextField
 */
public class IntegerTextField extends TextField {

	/**
     * Default constructor
     */
    public IntegerTextField() {
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text))
            super.replaceText(start, end, text);
    }

    @Override
    public void replaceSelection(String replacement) {
        if (validate(replacement))
        	if (replacement.isEmpty())
        		super.replaceSelection(replacement);
        	else if (Integer.parseInt(replacement) > 0)
        		super.replaceSelection(replacement);
    }

    /**
     * Validates that the TextField to be either an Integer or blank.
     *
     * @param TextField to check.
     * @return True if the the TextField is either an Integer or blank.
     */
    private boolean validate(String text) {
        if (text.isEmpty())
            return true;
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
