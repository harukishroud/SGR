/*
 SGR ALPHA - CONTROLLER PACKAGE
 File: ValidarCPF.JAVA | Last Major Update: 03.06.2015
 Developer: Rafael Sousa
 IDINALOG REBORN © 2015
 */
package sgr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmail {

    public static boolean isValidateEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static void main(String[] args) {
        String email = "rafinha@user.com";
        String email2 = "emailinvalido";
        if (isValidateEmail(email)) {
            System.out.println("email valido");
        } else {
            System.out.println("email invalido");
        }

        if (isValidateEmail(email2)) {
            System.out.println("email valido");
        } else {
            System.out.println("email invalido");
        }
    }
}
