import java.util.regex.Pattern;

public class MyString {

    public static void main(String[] args) {
        System.out.println(processString("\"ab\\cd\""));
    }

    private static String processString(String parsedString) {
        if (parsedString.length() > 1024) {
            System.out.println(("String constant too long"));
        }

        if (parsedString.contains("\u0000")) {
            System.out.println(("String contains null character"));
        }

        StringBuilder trimmed = new StringBuilder(parsedString);
        trimmed.deleteCharAt(0);
        trimmed.deleteCharAt(trimmed.length() - 1);
        StringBuilder finalResultBuilder = new StringBuilder();

        for (int i = 0; i < trimmed.length(); i++) {
            char currentChar = trimmed.charAt(i);

            if (currentChar == '\\' && i + 1 < trimmed.length()) {
                char nextChar = trimmed.charAt(i + 1);

                switch (nextChar) {
                    case 'n':
                        finalResultBuilder.append('\n');
                        break;
                    case 't':
                        finalResultBuilder.append('\t');
                        break;
                    case 'b':
                        finalResultBuilder.append('\b');
                        break;
                    case 'f':
                        finalResultBuilder.append('\f');
                        break;
                    case 'r':
                        finalResultBuilder.append('\r');
                        break;
                    default:
                        finalResultBuilder.append(nextChar);
                }

                i++; // Skip the next character
            } else {
                finalResultBuilder.append(currentChar);
            }
        }

        return finalResultBuilder.toString();
    }
}
