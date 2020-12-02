package comp3111.popnames;

import javafx.scene.control.TextField;

public class IntegerTextField extends TextField {

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
        	if (Integer.parseInt(replacement) > 0)
        		super.replaceSelection(replacement);
    }

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
