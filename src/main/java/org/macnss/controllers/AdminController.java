package org.macnss.controllers;

import org.macnss.Services.AdminService;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.UniqueCodeGenerator;
import org.macnss.helpers.Validator;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class AdminController extends Controller {
    AdminService adminService = new AdminService();

    public  void index(){


        PrintStatement.opening("Admin Panel");
        try {

            boolean isRunning = true;

            while (isRunning){
                PrintStatement.adminOptions();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> isRunning = false;
                        case 1 -> this.createAgent();
                        case 2 -> this.updateAgent();
                        case 3 -> this.getAgent();
                        case 4 -> this.getAllAgents();
                        case 5 -> this.deleteAgent();
                    }

                }
                else{
                    System.out.println("\nInvalid Entry , Choose one of the following options: ");
                }
            }

        }catch (Exception e){
            System.out.println("Crashed : "+e);
        }

    }

  
    public void login(){

        System.out.println("Login as admin , Enter your creadentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);


        try {
           if(adminService.login(email, password) != null){
               Admin admin = adminService.login(email, password);
               this.index();
           }else {

               System.out.println("Admin not found .");
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public void createAgent(){
        Agent agent = new Agent();
        System.out.println("Create new Agent Account");
        System.out.print("-> Name : ");
        String name = scanner.nextLine();
        PrintStatement.validateNameStatement(name, "Name");

        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);

        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        agent.setId(UniqueCodeGenerator.code());
        agent.setName(name);
        agent.setEmail(email);
        agent.setPassword(password);


        if(adminService.createAgent(agent) != null){
            System.out.println("Agent has been created successfully");

        }else{
            System.out.println("Creation of Agent has been Failed");
        }
    }

    public void updateAgent(){

        System.out.println("Update Agent Account");

        System.out.print("-> Enter agent id : ");
        String agentId = scanner.nextLine();
        PrintStatement.validateIdStatement(agentId, "agent");

        if(adminService.getAgent(agentId) != null ){
            Agent agent = adminService.getAgent(agentId);
            System.out.println("Update :");
            System.out.print("-> Name : ");
            String name = scanner.nextLine();
            PrintStatement.validateNameStatement(name, "Name");

            System.out.print("-> Email : ");
            String email = scanner.nextLine();
            PrintStatement.validateEmailStatement(email);

            System.out.print("-> Password : ");
            String password = scanner.nextLine();
            PrintStatement.validatePasswordStatement(password);

            agent.setName(name);
            agent.setEmail(email);
            agent.setPassword(password);

            System.out.println(agent.toString());

            if(adminService.updateAgent(agent) != null){
                System.out.println("Agent has been updated successfully");
            }else{
                System.out.println("Update of Agent has been Failed");
            }

        }else{
            System.out.println("No agent found with the provided id .");
        }








    }

    public void getAgent(){
        System.out.print("-> Enter agent id : ");
        String agentId = scanner.nextLine();
        PrintStatement.validateIdStatement(agentId, "agent");

        if(adminService.getAgent(agentId) != null ){
            System.out.println(adminService.getAgent(agentId).toString());
        }else{
            System.out.println("No agent found with the provided id .");
        }

    }

    public void getAllAgents(){
        adminService.getAllAgents().forEach(System.out::println);
    }


    public void deleteAgent() {
        System.out.print("-> Enter agent id : ");
        String agentId = scanner.next();
        PrintStatement.validateIdStatement(agentId , "agent");
        if (adminService.getAgent(agentId) != null) {

            if (adminService.deleteAgent(agentId)) {
                System.out.println("Agent has been deleted successfully .");
            } else {
                System.out.println("Delete of Agent has been Failed");
            }

        } else {
            System.out.println("No agent found with the provided id .");

        }
    }
}
