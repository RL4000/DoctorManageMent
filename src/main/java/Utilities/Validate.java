/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Common.ConsoleColors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    static Pattern p1 = Pattern.compile("^[0-9]{10}$");
    static Pattern p2 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
    static Pattern p3 = Pattern.compile("^[0-9]{3}.[0-9]{3}.[0-9]{4}$");
    static Pattern p4 = Pattern.compile("^[0-9]{3} [0-9]{3} [0-9]{4}$");
    static Pattern p5 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4} [e|ext][0-9]{4}$");
    static Pattern p6 = Pattern.compile("^\\([0-9]{3}\\)-[0-9]{3}-[0-9]{4}$");
    static Pattern patternUsername = Pattern.compile("^[A-Za-z][A-Za-z0-9]{4,}$");
    static Pattern patternPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,}$");
    
    /**
     *
     * @param MSG
     * @return A String must not empty
     * @throws IOException
     */
    
    public String getPassword(String MSG) throws IOException {
        while (true) {
            System.out.print(MSG);
            String check = in.readLine().trim();
            if (patternPassword.matcher(check).find()) {
                return check;
            } else {
                System.out.println(ConsoleColors.RED + "Wrong format! (Password >=6 char, include both number and char, not include any other type of char)");
                System.err.println("Enter again: ");
            }
        }
    }
    
    public String getUsername(String MSG) throws IOException {
        while (true) {
            System.out.print(MSG);
            String check = in.readLine().trim();
            if (patternUsername.matcher(check).find()) {
                return check;
            } else {
                System.out.println(ConsoleColors.RED + "Wrong format! (Password >=5 char, starts with character)");
                System.err.println("Enter again: ");
            }
        }
    }
   
    
    public String getString(String MSG) throws IOException {
        while (true) {
            System.out.print(MSG);
            String check = in.readLine().trim();
            if (check.isEmpty()) {
                System.err.println("Input is not empty");
                continue;
            } else {
                return check;
            }
        }
    }

    public int getINT(String MSG) throws IOException {
        while (true) {
            try {
                System.out.print(MSG);
                int number = Integer.parseInt(in.readLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"int\" type [" + Integer.MIN_VALUE + ", " + Integer.MAX_VALUE + "]");
            }
        }
    }

    public int getINT_LIMIT(String MSG, int MIN, int MAX) throws IOException {
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
            }
        }
    }

    public double getDOUBLE(String MSG) throws IOException {
        while (true) {
            try {
                System.out.print(MSG);
                double number = Double.parseDouble(in.readLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"double\" type [" + Double.MIN_VALUE + ", " + Double.MAX_VALUE + "]");
            }
        }
    }

    public double getDOUBLE_LIMIT(String MSG, double MIN, double MAX) throws IOException {
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
            }
        }
    }

    public float getFLOAT(String MSG) throws IOException {
        while (true) {
            try {
                System.out.print(MSG);
                float number = Float.parseFloat(in.readLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Enter \"float\" type [" + Float.MIN_VALUE + ", " + Float.MAX_VALUE + "]");
            }
        }
    }

    public float getFLOAT_LIMIT(String MSG, float MIN, float MAX) throws IOException {
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
            }
        }
    }

    public boolean getYesNo(String MSG) throws IOException {
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

    public boolean getUpdateDelete(String MSG) throws IOException {
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

    public String getEmail(String MSG) throws IOException {
        while (true) {
            String email = getString(MSG);
            if (email.matches(EMAIL_VALID)) {
                return email;
            } else {
                System.err.println("Email with format <account_name>@<domain>");
            }
        }
    }

    public String getPhone(String MSG) throws IOException {
        while (true) {
            String phone = getString(MSG);
            if (p1.matcher(phone).find() || p2.matcher(phone).find()
              || p3.matcher(phone).find() || p4.matcher(phone).find()
              || p5.matcher(phone).find() || p6.matcher(phone).find()) {
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
     * @throws IOException 
     */
    public Date getDate(String MSG) throws IOException {
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
     * @throws IOException 
     */
    public Date getDate_LimitToCurrent(String MSG) throws IOException {
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

    public int getAge(Date birthDay) {
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