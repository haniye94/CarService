package ir.tehranOil.app;

import java.util.HashMap;
import java.util.Map;

public class PLakUtils {
    private static final Map<Character, Character> persianToEnglishMap = new HashMap<>();

    static {
        persianToEnglishMap.put('۰', '0');
        persianToEnglishMap.put('۱', '1');
        persianToEnglishMap.put('۲', '2');
        persianToEnglishMap.put('۳', '3');
        persianToEnglishMap.put('۴', '4');
        persianToEnglishMap.put('۵', '5');
        persianToEnglishMap.put('۶', '6');
        persianToEnglishMap.put('۷', '7');
        persianToEnglishMap.put('۸', '8');
        persianToEnglishMap.put('۹', '9');
    }

    public static String convertPersianToEnglish(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (persianToEnglishMap.containsKey(ch)) {
                result.append(persianToEnglishMap.get(ch));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }


}
