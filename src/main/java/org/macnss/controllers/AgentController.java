package org.macnss.controllers;

import org.macnss.Services.AgentService;
import org.macnss.Services.EmailService;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.Validator;

import java.sql.SQLException;

public class AgentController extends Controller{

    AgentService agentService = new AgentService();


    public  void index(){

        PrintStatement.opening("Agent Panel");
        try {

            boolean isRunning = true;

            while (isRunning){
                PrintStatement.adminOptions();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> isRunning = false;

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

        System.out.println("Login as Agent, Enter your creadentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        if(agentService.login(email, password) != null){
            Agent agent = agentService.login(email, password);
            String code = "236565";
            String text = "Code Verification sent by MaCnss : "+ code;
            EmailService.sendEmail(text, "code verification ", agent.getEmail());
            System.out.print("\nWe send you a code to your email , Please enter your code verification : \n->");
            String codeVer = scanner.nextLine();
            if(codeVer.equals(code)){
                this.index();
            }else{
                System.out.println("\nCode verification wrong .");
                PrintStatement.backToMenu();
            }

        }else {
            System.out.println("Admin not found .");

        }
    }

}
