package org.macnss.helpers;

import org.macnss.controllers.Controller;

public class PrintStatement extends Controller {

    public static void rectangle(String text){


    }

    public static void field(){
        System.out.println("----------------------------------------------");
        System.out.println("\n-> Tap any key to continue  , Entre 0 to back to menu ?");
        if(!scanner.nextLine().equals("0")){

            System.out.println("\n-> Create new agent account : ");

            System.out.print("\n-> Name : ");
            String name  = scanner.nextLine();
            validateNameStatement(name);

            System.out.print("-> Email : ");
            String email  = scanner.nextLine();

            validateEmailStatement(email);

            System.out.print("-> Password : ");
            String password  = scanner.nextLine();

            validatePasswordStatement(password);
        }else{
            backToMenu();

        }


    }


    public static void backToMenu(){
        System.out.println("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .\n");
        scanner.nextLine();
    }

    public  static void validateNameStatement(String name){
        if(!Validator.validString(name)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nYou can't create an agent name using numbers - use letters .");
                System.out.print("-> Name : ");
                name  = scanner.nextLine();
                if(Validator.validString(name)){
                    confirmName = false;
                }
            }
        }

    }

    public  static void validateEmailStatement(String email){
        if(!Validator.validEmail(email)){
            boolean confirmEmail = true;
            while (confirmEmail){
                System.out.println("\nYou have to provide a valid email ex: example@test.com .");
                System.out.print("-> Email : ");
                email  = scanner.nextLine();
                if(Validator.validString(email)){
                    confirmEmail = false;
                }
            }
        }
    }

    public  static void validatePasswordStatement(String password){
        if(!Validator.validString(password)){
            boolean confirmPassword = true;
            while (confirmPassword){
                System.out.println("\nYou can't create an agent name using numbers - use letters .");
                System.out.print("-> Email : ");
                password  = scanner.nextLine();
                if(Validator.validString(password)){
                    confirmPassword = false;
                }
            }
        }
    }
}
