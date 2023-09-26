package org.macnss.helpers;

import org.macnss.controllers.Controller;

public class PrintStatement extends Controller {

    public static void  opening(String text){
        System.out.println();
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|\t\t\t\t\t\t "+ text +" \t\t\t\t\t\t\t |");
        System.out.println("|----------------------------------------------------------------|\n");
    }

    public static void options(){
        System.out.println("\nYou want to :");
        System.out.println("1 - Log in as Admin .");
        System.out.println("2 - Log in as Agent .");
        System.out.println("3 - Shows Folders History .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void adminOptions(){
        System.out.println("\nAs an Admin you can : ");
        System.out.println("1 - Create new agent .");
        System.out.println("2 - Update an agent .");
        System.out.println("3 - Get an agent .");
        System.out.println("4 - Get all agents .");
        System.out.println("5 - delete an agent .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
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
        System.out.println("\n-> Tap any key to back to menu .\n");
        scanner.nextLine();
    }

    public  static void validateIdStatement(String id){
        if(!Validator.validString(id)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nYou can't create an agent id using numbers - use letters .");
                System.out.print("-> Id : ");
                id  = scanner.nextLine();
                if(Validator.validString(id)){
                    confirmName = false;
                }
            }
        }

    }

    public  static void validateNameStatement(String name){
        if(!Validator.validName(name)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nYou can't create an agent name using numbers - use letters .");
                System.out.print("-> Name : ");
                name  = scanner.nextLine();
                if(Validator.validName(name)){
                    confirmName = false;
                }
            }
        }

    }

    public  static void validateEmailStatement(String email){
        if(!Validator.validEmail(email)){
            boolean confirmEmail = true;
            while (confirmEmail){
                System.out.println("You have to provide a valid email ex: example@test.com .");
                System.out.print("-> Email : ");
                email  = scanner.nextLine();
                if(Validator.validEmail(email)){
                    confirmEmail = false;
                }
            }
        }
    }

    public  static void validatePasswordStatement(String password){
        if(!Validator.validName(password)){
            boolean confirmPassword = true;
            while (confirmPassword){
                System.out.println("\nInvalid Entry , Format not accepted, use numbers & letters .");
                System.out.print("-> Email : ");
                password  = scanner.nextLine();
                if(Validator.validString(password)){
                    confirmPassword = false;
                }
            }
        }
    }
}
