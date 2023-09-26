package org.macnss.controllers;

import org.macnss.Services.AgentService;
import org.macnss.entity.Admin;
import org.macnss.helpers.PrintStatement;

import java.sql.SQLException;

public class AgentController extends Controller{

    AgentService agentService = new AgentService();

    public  boolean login(){

        System.out.println("Login as Agent, Enter your creadentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);



        try {
            if(agentService.login(email, password)){
               return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void create(){
        System.out.println("----------------------------------------------");
        System.out.println("\n-> Tap any key to continue  , Entre 0 to back to menu ?");
        if(!scanner.nextLine().equals("0")){

            System.out.println("\n-> Create new agent account : ");

            System.out.print("\n-> Name : ");
            String name  = scanner.nextLine();
            PrintStatement.validateNameStatement(name);

            System.out.print("-> Email : ");
            String email  = scanner.nextLine();

            PrintStatement.validateEmailStatement(email);

            System.out.print("-> Password : ");
            String password  = scanner.nextLine();

            PrintStatement.validatePasswordStatement(password);
        }else{
            PrintStatement.backToMenu();

        }
    }

}
