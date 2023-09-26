package org.macnss.controllers;

import org.macnss.Services.AdminService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminController extends Controller {
    AdminService adminService = new AdminService();

    public  boolean login(){
        // Helpers.drawRec()
        System.out.println("\nLogin As Admin .");
        System.out.println();
        System.out.println("Enter your credentials :");
        System.out.print("-> email : ");
        String email = scanner.nextLine();
        System.out.print("-> password : ");
        String password = scanner.nextLine();

       try {
           if(adminService.login(email, password)){
               return true;
           }else {

               return false;
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public void createAgent(){
        System.out.println("Create new Agent Account");

    }

    public void updateAgent(){

    }

    public void getAgent(){

    }

    public void getAllAgents(){

    }

    public void deleteAgent(){

    }

}
