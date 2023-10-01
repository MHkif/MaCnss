package org.macnss.controllers;

import org.macnss.Enum.FolderStatus;
import org.macnss.Services.AgentService;
import org.macnss.Services.EmailService;
import org.macnss.Services.FolderService;
import org.macnss.entity.*;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.UniqueCodeGenerator;
import org.macnss.helpers.Validator;

public class AgentController extends Controller{

    private final AgentService agentService = new AgentService();
   private  FolderController folderController = new FolderController();
    Agent agent;


    public  void index(Agent agent){

        try {

            boolean isRunning = true;

            while (isRunning){
                PrintStatement.opening("Agent Panel");
                PrintStatement.agentOptions();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> isRunning = false;
                        case 1 -> folderController.createFolder(agent);
                        case 2 -> folderController.getFolder();
                        case 3 -> folderController.getAllFolder();

                    }
                }
                else{
                    System.out.println("\nInvalid Entry , Choose one of the following options: ");
                }
            }

        }catch (Exception e){
            System.out.println("Crashed : "+ e.getCause());
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
             agent = agentService.login(email, password);
            String code = "236565";
            String text = "Code Verification sent by MaCnss : "+ code;
            EmailService.sendEmail(text, "code verification ", agent.getEmail());
            System.out.print("\nWe send you a code to your email , Please enter your code verification : \n->");
            String codeVer = scanner.nextLine();
            if(codeVer.equals(code)){
                this.index(agent);
            }else{
                agent = null;
                System.out.println("\nCode verification wrong .");
                PrintStatement.backToMenu();
            }

        }else {
            System.out.println("Agent not found .");

        }
        
    }
}
