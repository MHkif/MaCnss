package org.macnss.controllers;

import org.macnss.Enum.FolderStatus;
import org.macnss.Services.FolderService;
import org.macnss.entity.Agent;
import org.macnss.entity.Document;
import org.macnss.entity.Folder;
import org.macnss.entity.Patient;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.UniqueCodeGenerator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FolderController extends Controller {
    private final FolderService folderService = new FolderService();
    public void createFolder(){
        Folder folder = new Folder();
//        Timestamp depositDate = new Timestamp(System.currentTimeMillis());
//        Date currentDate = new Date();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        System.out.println("Create new folder");
        System.out.print("-> matricule of patient : ");
        String matricule = scanner.nextLine();
        System.out.print("-> agent id : ");
        String agentId = scanner.nextLine();
        PrintStatement.validateIdStatement(agentId);
        System.out.print("-> Name : ");
        String name = scanner.nextLine();
        PrintStatement.validateNameStatement(name);
        System.out.println("Add Document");
        System.out.print("-> title : ");
        String titleDoc = scanner.nextLine();



        String uniqueCode = UniqueCodeGenerator.generateUniqueCode();
        folder.setId(uniqueCode);
        folder.setFolder_name(name);
        folder.setDepositDate(currentDate);
        folder.setFolderStatus(FolderStatus.WAITING);
        Patient patient = new Patient();
        patient.setMatricule(matricule);
        folder.setPatient(patient);
        folder.setReturn_price(null);
        Agent agent = new Agent();
        agent.setId(agentId);
        folder.setAgent(agent);

        Document document = new Document();



        document.setDocument_id(uniqueCode);
        document.setTitle(titleDoc);
        document.setDate_created(currentDate);
        document.setFolder(folder);


        if(folderService.createFolder(folder,matricule) != null){
            System.out.println("folder has been created successfully");

            if(folderService.saveDocument(document,folder.getId()) != null){
                System.out.println("Document has been created successfully");
            }else{
                System.out.println("Creation of Document has been Failed");
            }

        }else{
            System.out.println("Creation of folder has been Failed");
        }
    }

    public  org.macnss.entity.Document saveDocument(Folder folder){
         org.macnss.entity.Document document = new org.macnss.entity.Document();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        System.out.println("Add Document");
        System.out.print("-> title : ");
        String title = scanner.nextLine();

        String uniqueCode = UniqueCodeGenerator.generateUniqueCode();
        document.setDocument_id(uniqueCode);
        document.setTitle(title);
        document.setDate_created(currentDate);
        document.setFolder(folder);

        if(folderService.saveDocument(document,folder.getId()) != null){
            System.out.println("Document has been created successfully");
        }else{
            System.out.println("Creation of Document has been Failed");
        }

        return document;
    }
}
