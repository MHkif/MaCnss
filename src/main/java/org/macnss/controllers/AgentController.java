package org.macnss.controllers;

import org.macnss.Enum.FolderStatus;
import org.macnss.Services.AgentService;
import org.macnss.Services.EmailService;
import org.macnss.Services.FolderService;
import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;
import org.macnss.entity.Folder;
import org.macnss.entity.Patient;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.UniqueCodeGenerator;
import org.macnss.helpers.Validator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class AgentController extends Controller{

    AgentService agentService = new AgentService();
    FolderService folderService = new FolderService();
    Agent agent ;


    public  void index(){

        PrintStatement.opening("Agent Panel");
        try {

            boolean isRunning = true;

            while (isRunning){
                PrintStatement.agentOptions();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> isRunning = false;
                        case 1 -> this.createFolder();

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
             agent = agentService.login(email, password);
            String code = "236565";
            String text = "Code Verification sent by MaCnss : "+ code;
            EmailService.sendEmail(text, "code verification ", agent.getEmail());
            System.out.print("\nWe send you a code to your email , Please enter your code verification : \n->");
            String codeVer = scanner.nextLine();
            if(codeVer.equals(code)){
                this.index();
            }else{
                agent = null;
                System.out.println("\nCode verification wrong .");
                PrintStatement.backToMenu();
            }

        }else {
            System.out.println("Admin not found .");

        }


        
    }

    public void createFolder(){
        Folder folder = new Folder();
//        Date currentDate = new Date();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        System.out.println("Create new folder");
        System.out.print("-> matricule of patient : ");
        String matricule = scanner.nextLine();
        PrintStatement.validateIdStatement(matricule);
//        System.out.print("-> agent id : ");
//        String agentId = scanner.nextLine();
//        PrintStatement.validateNameStatement(agent.getId());
        System.out.print("-> Name : ");
        String name = scanner.nextLine();
        PrintStatement.validateNameStatement(name);



        String uniqueCode = UniqueCodeGenerator.generateUniqueCode();
        folder.setId(uniqueCode);
        folder.setFolder_name(name);
        folder.setDepositDate(currentDate);
        folder.setFolderStatus(FolderStatus.WAITING);
        Patient patient = new Patient();
        patient.setMatricule(matricule);
        folder.setPatient(patient);
        folder.setReturn_price(null);

        folder.setAgent(agent);




        if(folderService.createFolder(folder,matricule) != null){
            System.out.println("folder has been created successfully");
        }else{
            System.out.println("Creation of folder has been Failed");
        }
    }

}
