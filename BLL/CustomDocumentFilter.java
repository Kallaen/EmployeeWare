package BLL;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class CustomDocumentFilter {

    public static class CharactersOnlyFilter extends javax.swing.text.DocumentFilter {
        @Override
        public void insertString(javax.swing.text.DocumentFilter.FilterBypass fp, int offset,
                                 String stringToFilter, AttributeSet set)
                throws BadLocationException {
            int len = stringToFilter.length();
            if (Character.isLetter(stringToFilter.charAt(len - 1)))
                super.insertString(fp, offset, stringToFilter, set);
        }

        @Override
        public void replace(javax.swing.text.DocumentFilter.FilterBypass fp, int offset, int length,
                            String stringToFilter, AttributeSet set)
                throws BadLocationException {
            int len = stringToFilter.length();
            if (Character.isLetter(stringToFilter.charAt(len - 1)))
                super.replace(fp, offset, length, stringToFilter, set);
        }
    }

    public static class NumbersOnlyFilter extends javax.swing.text.DocumentFilter {
        public void insertString(javax.swing.text.DocumentFilter.FilterBypass fb, int offset,
                                 String text, AttributeSet set) throws BadLocationException {
            for (char c : text.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return;
                }
            }
            fb.insertString(offset, text.toUpperCase(), set);
        }

        public void replace(javax.swing.text.DocumentFilter.FilterBypass fb, int offset,
                            int length, String text, AttributeSet set)
                throws BadLocationException {
            for (char c : text.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return;
                }
            }
            fb.replace(offset, length, text.toUpperCase(), set);
        }
    }
}
