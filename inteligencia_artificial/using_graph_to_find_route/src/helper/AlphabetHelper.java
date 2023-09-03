package helper;

import java.util.Locale;

public class AlphabetHelper {
    private static char[] getAlphabet() {
        return getAlphabet(false);
    }

    private static char[] getAlphabet(boolean flagToUpperCase) {
        LocaleLanguage language = LocaleLanguage.getLocalLanguage(Locale.of("pt", "MT"));
        return getAlphabet(language, flagToUpperCase);
    }

    private static char[] getAlphabet(LocaleLanguage localeLanguage, boolean flagToUpperCase) {
        if (localeLanguage == null)
            localeLanguage = LocaleLanguage.ENGLISH;

        char firstLetter = localeLanguage.getFirstLetter();
        char lastLetter = localeLanguage.getLastLetter();
        int alphabetSize = lastLetter - firstLetter + 1;

        char[] alphabet = new char[alphabetSize];

        for (int index = 0; index < alphabetSize; index++) {
            alphabet[index] = (char) (index + firstLetter);
        }

        if (flagToUpperCase) {
            alphabet = new String(alphabet).toUpperCase().toCharArray();
        }

        return alphabet;
    }
}
