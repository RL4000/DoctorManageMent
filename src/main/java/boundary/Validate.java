/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author nanht
 */
public final class Validate {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //getMail
    private static final String EMAIL_VALID = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";

    //getPhone
    private static final Pattern P1 = Pattern.compile("^[0-9]{10}$");
    private static final Pattern P2 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
    private static final Pattern P3 = Pattern.compile("^[0-9]{3}.[0-9]{3}.[0-9]{4}$");
    private static final Pattern P4 = Pattern.compile("^[0-9]{3} [0-9]{3} [0-9]{4}$");
    private static final Pattern P5 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4} [e|ext][0-9]{4}$");
    private static final Pattern P6 = Pattern.compile("^\\([0-9]{3}\\)-[0-9]{3}-[0-9]{4}$");
    private static final Pattern PATTERN_USER_NAME = Pattern.compile("^[A-Za-z][A-Za-z0-9]{4,}$");
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,}$");

    /**
     *
     * @param MSG
     * @return A String must not empty
     * @
     */
    public static String getPassword(String MSG) {
        while (true) {
            try {
                System.out.print(MSG);
                String check = in.readLine().trim();
                if (PATTERN_PASSWORD.matcher(check).find()) {
                    return check;
                } else {
                    System.out.println(ConsoleColors.RED + "Wrong format! (Password >=6 char, include both number and char, not include any other type of char)");
                    System.err.println("Enter again: ");
                }
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getUsername(String MSG) {
        while (true) {
            try {
                System.out.print(MSG);
                String check = in.readLine().trim();
                if (PATTERN_USER_NAME.matcher(check).find()) {
                    return check;
                } else {
                    System.out.println(ConsoleColors.RED + "Wrong format! (Password >=5 char, starts with character)");
                    System.err.println("Enter again: ");
                }
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getString(String MSG) {
        while (true) {
            try {
                System.out.print(MSG);
                String check = in.readLine().trim();
                if (check.isEmpty()) {
                    System.err.println("Input is not empty");
                } else {
                    return check;
                }
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int getINT(String MSG) {
        while (true) {
            try {
                System.out.print(MSG);
                int number = Integer.parseInt(in.readLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"int\" type [" + Integer.MIN_VALUE + ", " + Integer.MAX_VALUE + "]");
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int getINT_LIMIT(String MSG, int MIN, int MAX) {
        while (true) {
            try {
                System.out.print(MSG);
                int number = Integer.parseInt(in.readLine());
                if (number < MIN || number > MAX) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Valid input are in the range of[" + MIN + ", " + MAX + "]. ");
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static double getDOUBLE(String MSG) {
        while (true) {
            try {
                System.out.print(MSG);
                double number = Double.parseDouble(in.readLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"double\" type [" + Double.MIN_VALUE + ", " + Double.MAX_VALUE + "]");
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static double getDOUBLE_LIMIT(String MSG, double MIN, double MAX) {
        while (true) {
            try {
                System.out.print(MSG);
                double number = Double.parseDouble(in.readLine());
                if (number < MIN || number > MAX) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"double\" about [" + MIN + ", " + MAX + "]");
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static float getFLOAT(String MSG) {
        while (true) {
            try {
                System.out.print(MSG);
                float number = Float.parseFloat(in.readLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"float\" type [" + Float.MIN_VALUE + ", " + Float.MAX_VALUE + "]");
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static float getFLOAT_LIMIT(String MSG, float MIN, float MAX) {
        while (true) {
            try {
                System.out.print(MSG);
                float number = Float.parseFloat(in.readLine());
                if (number < MIN || number > MAX) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"float\" about [" + MIN + ", " + MAX + "]");
            } catch (IOException ex) {
                Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean getYesNo(String MSG) {
        while (true) {
            String check = getString(MSG);
            if (check.equalsIgnoreCase("Y")) {
                return true;
            }
            if (check.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Enter Y/N(y/n)");
        }
    }

    public static boolean getUpdateDelete(String MSG) {
        while (true) {
            String check = getString(MSG);
            if (check.equalsIgnoreCase("U")) {
                return true;
            }
            if (check.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Enter U/D(u/d)");
        }
    }

    public static String getEmail(String MSG) {
        while (true) {
            String email = getString(MSG);
            if (email.matches(EMAIL_VALID)) {
                return email;
            } else {
                System.err.println("Email with format <account_name>@<domain>");
            }
        }
    }

    public static String getPhone(String MSG) {
        while (true) {
            String phone = getString(MSG);
            if (P1.matcher(phone).find() || P2.matcher(phone).find()
                    || P3.matcher(phone).find() || P4.matcher(phone).find()
                    || P5.matcher(phone).find() || P6.matcher(phone).find()) {
                return phone;
            } else {
                if (getYesNo("Do you want show fomat: ")) {
                    System.err.println("Enter\n1234567890\n"
                            + "123-456-7890\n"
                            + "123.456.7890\n"
                            + "123 456 7890\n"
                            + "(123)-456-7890\n"
                            + "123-456-7890 x1234\n"
                            + "123-456-7890 ext1234");
                } else {
                    System.err.println("Enter again");
                }
            }
        }
    }

    /**
     *
     * @param MSG
     * @return A day is not limit
     * @
     */
    public static Date getDate(String MSG) {
        Date now = new Date();
        while (true) {
            String check = getString(MSG);
            SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
            fd.setLenient(false);
            try {
                Date date = fd.parse(check);
                return date;
            } catch (ParseException e) {
                System.err.println("That day was not found");
            }
        }
    }

    /**
     *
     * @param MSG
     * @return A day is limit with current
     * @
     */
    public static Date getDate_LimitToCurrent(String MSG) {
        Date now = new Date();
        while (true) {
            String check = getString(MSG);
            SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
            fd.setLenient(false);
            try {
                Date date = fd.parse(check);
                if (date.before(now)) {
                    return date;
                }
                System.err.println("Enter \"Date\" type before " + fd.format(now));
            } catch (ParseException e) {
                System.err.println("That day was not found");
            }
        }
    }

    public static Date getDate_LimitFromCurrent(String MSG) {
        Date now = new Date();
        while (true) {
            String check = getString(MSG);
            SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
            fd.setLenient(false);
            try {
                Date date = fd.parse(check);
                if (date.after(now)) {
                    return date;
                }
                System.err.println("Enter \"Date\" type before " + fd.format(now));
            } catch (ParseException e) {
                System.err.println("That day was not found");
            }
        }
    }

    public static LocalDateTime toLocalDateTime(Date theDate) {
        return LocalDateTime.ofInstant(theDate.toInstant(), ZoneId.systemDefault());
    }

    public static int getAge(Date birthDay) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();

        //Sets this Calendar's time with the given
        birth.setTime(birthDay);

        if (birth.after(now)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        //Calculator age
        int age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int month1 = now.get(Calendar.MONTH);
        int month2 = birth.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        } else {
            if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = birth.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        }
        return age;
    }
}
