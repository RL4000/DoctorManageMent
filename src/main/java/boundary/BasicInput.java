package boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BasicInput {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static String userPrompt = "| Please enter again: ";

    public BasicInput() {
    }

    public BasicInput(String userPrompt) {
        this.userPrompt = userPrompt;
    }

    // Date = 2021/02/01
//<editor-fold defaultstate="collapsed" desc="String input">
    /**
     * From keyboard read a string
     * @return 
     */
    public static String getStringAllowNull() {
        String result = null;
        try {
            result = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(BasicInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * From keyboard reads and return a not null string
     *
     * @return A not null string
     */
    public static String getString() {
        String userString;
        while (true) {
            try {
                userString = in.readLine().trim();
                if (!userString.isEmpty()) {
                    return userString;
                } else {
                    System.out.printf("\tType something in %s", userPrompt);
                }
            } catch (IOException ex) {
                Logger.getLogger(BasicInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * From keyboard read a string that matches regex
     *
     * @param regex
     * @param customMessage
     * @return A not null string matches regex
     */
    public static String getString(String regex, String customMessage) {
        String userString;
        while (true) {
            userString = getString();
            if (userString.matches(regex)) {
                return userString;
            } else {
                System.out.printf("\t%s %s", customMessage, userPrompt);
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Integer input">
    /**
     * From keyboard reads an integer
     *
     * @return An integer
     */
    public static int getInt() {
        int userInteger;
        userInteger = Integer.parseInt(getString("[\\+\\-]{0,1}([0-9])+", "Only enter integer number"));
        return userInteger;
    }

    /**
     * From keyboard reads an integer from min to max
     *
     * @param min
     * @param max
     * @return An integer &gt;= min and &lt;= max
     */
    public static int getInt(int min, int max) {
        int userInteger;
        while (true) {
            userInteger = getInt();
            if ((userInteger >= min) && (userInteger <= max)) {
                return userInteger;
            } else {
                System.out.printf("\tMust be between %d and %d %s", min, max, userPrompt);
            }
        }
    }

    /**
     * From keyboard enter an integer that's not in array
     *
     * @param notAllow
     * @return
     */
    public static int getIntNot(int[] notAllow) {
        boolean valid;
        int userInteger;
        while (true) {
            valid = true;
            userInteger = getInt();
            for (int i = 0; i < notAllow.length; i++) {
                if (userInteger == notAllow[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return userInteger;
            } else {
                System.out.printf("\tAny number but %d", notAllow[0]);
                for (int i = 1; i < notAllow.length; i++) {
                    System.out.printf(", %d", notAllow[i]);
                }
                System.out.printf(" %s", userPrompt);
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Double input">
    /**
     * From keyboard reads an double
     *
     * @return An double
     */
    public static double getDouble() {
        double userDouble;
        userDouble = Double.parseDouble(getString("[\\-\\+]{0,1}([0-9])+(.([0-9])+){0,1}", "Only enter double number"));
        return userDouble;
    }

    /**
     * From keyboard reads an double from min to max
     *
     * @param min
     * @param max
     * @return An double &gt;= min and &lt;= max
     */
    public static double getDouble(double min, double max) {
        double userDouble;
        while (true) {
            userDouble = getDouble();
            if ((userDouble >= min) && (userDouble <= max)) {
                return userDouble;
            } else {
                System.out.printf("\tMust be between %f and %f %s", min, max, userPrompt);
            }
        }
    }

    /**
     * From keyboard enter an double that's not in array
     *
     * @param notAllow
     * @return
     */
    public static double getDoubleNot(int[] notAllow) {
        boolean valid;
        double userDouble;
        while (true) {
            valid = true;
            userDouble = getDouble();
            for (int i = 0; i < notAllow.length; i++) {
                if (userDouble == notAllow[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return userDouble;
            } else {
                System.out.printf("\tAny number but %f", notAllow[0]);
                for (int i = 1; i < notAllow.length; i++) {
                    System.out.printf(", %f", notAllow[i]);
                }
                System.out.printf(" %s", userPrompt);
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Boolean input">
    public static boolean yesNo(char yes, char no, String customMessage) {
        String regex;
        regex = "[" + Character.toLowerCase(yes) + Character.toUpperCase(yes) + Character.toLowerCase(no) + Character.toUpperCase(no) + "]{1}";
        String choice;
        System.out.printf("%s? (%c/%c): ", customMessage, yes, no);
        choice = getString("[YNyn]{1}", "Only enter Y (yes) or N (no)");
        return choice.equalsIgnoreCase(yes + "");
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="LocalDateTime input">
    public static LocalDateTime getLocalDateTime() {
        LocalDateTime result;
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            try {
                result = LocalDateTime.parse(getString(), fm);
                return result;
            } catch (Exception e) {
                System.out.printf("\tShould be in format yyyy-mm-dd hh-mm-ss %s", userPrompt);
            }
        }
    }

    public static LocalDateTime getLocalDateTime(LocalDateTime min, LocalDateTime max) {
        while (true) {
            LocalDateTime result = getLocalDateTime();
            if ((result.isBefore(min)) || (result.isAfter(max))) {
                System.out.printf("\tMust be from %s to %s %s", min, max, userPrompt);
            } else {
                return result;
            }
        }
    }
//</editor-fold>

    // Custom inputs
}
