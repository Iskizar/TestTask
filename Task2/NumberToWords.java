package Task2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class NumberToWords {

    private final List<String> ONES = Arrays.asList("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять");
    private final List<String> TEENS = Arrays.asList("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семьнадцать", "восемьнадцать", "девятнадцать");
    private final List<String> TENS = Arrays.asList("", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто");
    private final List<String> HUNDREDS = Arrays.asList("", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот");
    private final List<String> THOUSANDS = Arrays.asList("", "тысяча", "тысячи", "тысяч");

    public String convert(BigDecimal amount) {
        String[] parts = amount.setScale(2).toString().split("\\.");
        String wholePart = parts[0];
        String fractionalPart = parts.length > 1 ? parts[1] : "00";

        String wholePartInWords = convertNumber(Integer.parseInt(wholePart));
        String fractionalPartInWords = convertNumber(Integer.parseInt(fractionalPart));

        // Формируем строку с правильными окончаниями
        return wholePartInWords + " " + getRubleForm(Integer.parseInt(wholePart)) + " " +
                fractionalPartInWords + " " + getKopeikaForm(Integer.parseInt(fractionalPart));
    }

    private String convertNumber(int amount) {
        if (amount == 0) return "ноль";
        StringBuilder words = new StringBuilder();

        // Обрабатываем тысячи
        int thousands = amount / 1000;
        if (thousands > 0) {
            words.append(convertThousandPart(thousands)).append(" ").append(getThousandsSuffix(thousands)).append(" ");
        }

        // Обрабатываем сотни
        amount %= 1000;
        int hundreds = amount / 100;
        if (hundreds > 0) {
            words.append(HUNDREDS.get(hundreds)).append(" ");
        }

        // Обрабатываем десятки и единицы
        amount %= 100;
        if (amount >= 10 && amount < 20) {
            words.append(TEENS.get(amount - 10)).append(" ");
        } else {
            int tens = amount / 10;
            if (tens > 0) {
                words.append(TENS.get(tens)).append(" ");
            }
            int ones = amount % 10;
            if (ones > 0) {
                words.append(ONES.get(ones)).append(" ");
            }
        }

        return words.toString().trim();
    }

    private String convertThousandPart(int amount) {
        int tens = amount / 10;
        int ones = amount % 10;

        StringBuilder result = new StringBuilder();
        // Обработка десятков

        if (amount > 10 && amount < 20) {
            return convertNumber(amount);
        }

        if (tens > 0) {
            result.append(TENS.get(tens)).append(" ");
        }

        // Обработка единиц: если единицы 1 или 2, добавляем "одна" или "две"
        if (ones == 1) {
            result.append("одна");
        } else if (ones == 2) {
            result.append("две");
        } else if (ones > 2) {
            result.append(ONES.get(ones));
        }
        return result.toString().trim();
    }


    private String getThousandsSuffix(int amount) {
        // Обработка склонения тысяч
        if (amount % 100 >= 11 && amount % 100 <= 14) {
            return THOUSANDS.get(3);  // "тысяч" для чисел от 11 до 14
        } else if (amount % 10 == 1) {
            return THOUSANDS.get(1);  // "тысяча"
        } else if (amount % 10 >= 2 && amount % 10 <= 4) {
            return THOUSANDS.get(2);  // "тысячи"
        } else {
            return THOUSANDS.get(3);  // "тысяч"
        }
    }

    private String getRubleForm(int amount) {
        if (amount % 10 == 1 && amount % 100 != 11) {
            return "рубль";
        } else if ((amount % 10 >= 2 && amount % 10 <= 4) && !(amount % 100 >= 12 && amount % 100 <= 14)) {
            return "рубля";
        } else {
            return "рублей";
        }
    }

    private String getKopeikaForm(int amount) {
        if (amount % 10 == 1 && amount % 100 != 11) {
            return "копейка";
        } else if ((amount % 10 >= 2 && amount % 10 <= 4) && !(amount % 100 >= 12 && amount % 100 <= 14)) {
            return "копейки";
        } else {
            return "копеек";
        }
    }
}
