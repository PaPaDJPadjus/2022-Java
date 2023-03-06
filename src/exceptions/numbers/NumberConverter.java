package exceptions.numbers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberConverter {

    private static Properties properties;
    private static String finalOutputString = "";

    public static void main(String[] args) {
    }

    public NumberConverter(String lang) {
        String formatLangFile = "src/exceptions/numbers/numbers_%s.properties".formatted(lang);

        properties = new Properties();
        FileInputStream is = null;

        try {
            is = new FileInputStream(formatLangFile);

            InputStreamReader reader = new InputStreamReader(
                    is, StandardCharsets.UTF_8);

            properties.load(reader);
            if (properties.isEmpty()) {
                throw new MissingTranslationException(lang);
            }
        } catch (IOException e) {
            throw new MissingLanguageFileException(lang, e);
        } catch (IllegalArgumentException e) {
            throw new BrokenLanguageFileException(lang, e);
        } finally {
            close(is);
        }
    }

    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {}
    }

    public static String numberInWords(Integer number) {

        finalOutputString = "";

        if (properties.containsKey(String.valueOf(number))) {
            return properties.getProperty(String.valueOf(number));
        }

        int numberHundreds = number / 100;
        int numberTens = number / 10 % 10;
        int numberOnes = number % 10;
        numberHundredsToWords(numberHundreds, numberTens, numberOnes);

        numberTeensAndIrregularsToWords(numberTens, numberOnes);

        if (properties.containsKey(String.valueOf(numberTens * 10)) && numberTens != 0 && numberTens != 1) {
            finalOutputString += properties.getProperty(String.valueOf(numberTens * 10));
        }
        else if (numberTens != 0 && numberTens != 1) {
            finalOutputString += properties.getProperty(String.valueOf(numberTens)) + properties.getProperty("tens-suffix");

        }
        if (numberOnes != 0 && numberTens != 0 && numberTens != 1) {
            finalOutputString += properties.getProperty("tens-after-delimiter");
            finalOutputString += properties.getProperty(String.valueOf(numberOnes));
        }
        return finalOutputString;
    }

    public static void numberHundredsToWords(int hundreds, int tens, int ones) {
        if (hundreds == 1 && tens == 0 && ones == 0) {
            finalOutputString += properties.getProperty(String.valueOf(hundreds))+ properties.getProperty("hundreds-before-delimiter") + properties.getProperty("hundred");
        }
        else if (hundreds >= 1) {
            finalOutputString += properties.getProperty(String.valueOf(hundreds)) + properties.getProperty("hundreds-before-delimiter") + properties.getProperty("hundred") + properties.getProperty("hundreds-after-delimiter");
            if (ones != 0 && tens == 0) {
                finalOutputString += properties.getProperty(String.valueOf(ones));
            }
            if (tens == 1 && ones == 0) {
                finalOutputString += properties.getProperty(String.valueOf(10));
            }
        }
    }

    public static void numberTeensAndIrregularsToWords (int tens, int ones) {
        if (tens != 0 && ones != 0) {
            if (properties.containsKey(String.valueOf(tens * 10 + ones))) {
                finalOutputString += properties.getProperty(String.valueOf(tens * 10 + ones));
            }
            if (!properties.containsKey(String.valueOf(tens * 10 + ones)) && tens == 1) {
                finalOutputString += properties.getProperty(String.valueOf(ones)) + properties.getProperty("teen");
            }
        }
    }
}

