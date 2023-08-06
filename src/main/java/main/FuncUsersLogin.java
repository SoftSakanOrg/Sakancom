package main;


import static main.Sakan.*;
import static main.FuncCheckEmail.checkemail;
import static main.funcCheckLogin.checklogin;
@SuppressWarnings("java:S1118")
public class FuncUsersLogin {

    public static void login(String usertype, String email, String pass) {



        if (Sakan.flag11 == 0) {




            if ( email.equalsIgnoreCase("1")) {
                mainfunc();

            }
            if (!email.contains("@") || !email.contains(".")) {
                logger.info("Please enter a valid email...");
               flaglogin=1;
                tenantfunc(usertype);

            }



            checkemail(email, 2, usertype);
            if (Sakan.flag11 == 0) {




                if ( pass.equalsIgnoreCase("1")) {
                    mainfunc();

                }


                checklogin(email, pass,usertype);
            }
            Sakan.flag11 =1;
            Sakan.onlineUser =email ;

        }






    }




}
