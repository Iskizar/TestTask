package Task2;

import java.math.BigDecimal;




public class Task2Main{
    public static void main(String[] args) {
        NumberToWords numberToWords = new NumberToWords();
        BigDecimal amount = new BigDecimal("54325.85");  // Пример числа
        System.out.println(numberToWords.convert(amount));
    }
}