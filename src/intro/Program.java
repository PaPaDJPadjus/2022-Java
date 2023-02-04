package intro;

public class Program {

    public static void main(String[] args) {

        int decimal = asDecimal("11001101");
        String binary = asString(205);

        System.out.println(decimal); // 205
        System.out.println(binary);
    }

    public static String asString(int input) {
        String result = "";

        while (input > 0) {
            if (input % 2 == 1) {
                result = '1' + result;
            }
            else {
                result = '0' + result;
            }
            input /= 2;
        }
        
        return result;
    }

    public static int asDecimal(String input) {
        int len_minus = input.length() - 1;
        char last_char;
        int place_counter = 0;
        int final_anwser = 0;

        for (int i = len_minus; i >= 0; i--) {
            last_char = input.charAt(i);
            if (last_char == '1') {
                final_anwser = (int) (final_anwser + pow(2, place_counter));
            }
            place_counter = place_counter + 1;
        }

        return final_anwser;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.
        int arg_powered = 1;

        if (arg == 0) {
            return 0;
        }
        if (power == 0) {
            return 1;
        }
        for (int i = 0; i < power; i++) {
            arg_powered *= arg;
        }
        return arg_powered;
    }
}
