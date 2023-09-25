package org.macnss.controllers;


import java.util.Scanner;

public class Navigator extends Controller {

    private final static AdminController adminController = new AdminController();

    public static void index(){
        System.out.println("You want to log as :");
        System.out.println("1 -> Admin");
        System.out.println("2 -> Agent");
        System.out.print("-> ");
        String option = scanner.nextLine();

        if(Integer.parseInt(option) == 1){
            if(adminController.login()){
                System.out.println("You are logged in successfully");
            }else{
                System.out.println("Login Failed ! Somthing went wrong ..");
            }
        }else{
            System.out.println("Login As Agent ");
        }
    }
}
