package org.macnss.controllers;

import org.macnss.Services.AdminService;
import org.macnss.entity.Agent;
import org.macnss.helpers.PrintStatement;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class AdminController extends Controller {
    AdminService adminService = new AdminService();

    public  boolean login(){

        PrintStatement.opening("Admin Panel");

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
        Agent agent = new Agent();
        System.out.println("Create new Agent Account");
        System.out.print("-> Name : ");
        String name = scanner.nextLine();
        PrintStatement.validateNameStatement(name);

        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);

        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        String uniqueID = UUID.randomUUID().toString();
        agent.setId(uniqueID);
        agent.setName(name);
        agent.setEmail(email);
        agent.setPassword(password);

        System.out.println(agent.toString());

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
        PrintStatement.validateIdStatement(agentId);

        if(adminService.getAgent(agentId) != null ){
            Agent agent = adminService.getAgent(agentId);
            System.out.println("Update :");
            System.out.print("-> Name : ");
            String name = scanner.nextLine();
            PrintStatement.validateNameStatement(name);

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
        PrintStatement.validateIdStatement(agentId);

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
        PrintStatement.validateIdStatement(agentId);
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
