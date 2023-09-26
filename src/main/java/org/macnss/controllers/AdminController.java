package org.macnss.controllers;

import org.macnss.Services.AdminService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminController extends Controller {
    AdminService adminService = new AdminService();

    public  boolean login(){

        System.out.println("\nEnter your creadentials :");
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


}
