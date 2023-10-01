package org.macnss.controllers;

import org.macnss.Enum.DocumentStatus;
import org.macnss.Enum.DocumentType;
import org.macnss.Services.DocumentService;
import org.macnss.Services.FolderService;
import org.macnss.Services.PatientService;
import org.macnss.entity.*;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.UniqueCodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Date;
import java.util.stream.Stream;

public class FolderController extends Controller {
    private final FolderService folderService = new FolderService();
    private final PatientService patientService = new PatientService();
    private final DocumentService documentService = new DocumentService();

    public void createFolder(Agent agent){
        Folder folder = new Folder();
        Patient patient;
        List<ADocument> documents = new ArrayList<>();


        System.out.println("======  Create new folder  ======");
        folder.setAgent(agent);
        folder.setId(UniqueCodeGenerator.code());
        folder.setDepositDate(new Date(System.currentTimeMillis()));

        System.out.print("-> Matriculate of patient : ");
        String matriculate = scanner.nextLine();
        PrintStatement.validateIdStatement(matriculate,"Matriculate of patient");

        patient = patientService.get(matriculate);

        if(Objects.isNull(patient)){
            boolean isNull = true;
            while (isNull){
                System.out.println("Patient not found , please provide a valid patient matriculate .");
                System.out.print("-> Matriculate of patient : ");
                matriculate = scanner.nextLine();
                PrintStatement.validateIdStatement(matriculate,"Matriculate of patient");
                if(Objects.nonNull(patientService.get(matriculate))){
                    patient = patientService.get(matriculate);
                    isNull = false;
                }
            }
        }else {
            folder.setPatient(patient);
            System.out.print("-> Name : ");
            String folder_name = scanner.nextLine();
            PrintStatement.validateNameStatement(folder_name, "Name");
            folder.setName(folder_name);

            addDocuments(folder).forEach(documents::add);
            addMedicines(folder).forEach(documents::add);
            addRadios(folder).forEach(documents::add);
            addScanners(folder).forEach(documents::add);

            double total = documents.stream()
                    .mapToDouble(ADocument::getRefund_rate)
                    .sum();

            // TODO : Calculate
            folder.setTotal_refund(Float.parseFloat(String.valueOf(total)));



            if(folderService.create(folder) != null){

                for (ADocument document: documents) {
                    if(documentService.create(document) != null){
                        System.out.println("Document "+ document.getTitle()+" has been created successfully");
                    }else{
                        System.out.println("Creation of Document "+ document.getTitle()+" has been Failed");
                    }
                }
                System.out.println("folder has been created successfully");

            }else{
                System.out.println("Creation of folder has been Failed");
            }
        }
    }

    public  List<ADocument> addDocuments(Folder folder){
        List<ADocument> documents = new ArrayList<>();
        System.out.println("To add Documents click any key , Enter on 0 ta exit .");
        String  doc_option  = scanner.nextLine();
        if(!doc_option.equals("0"))
        {
            Document document = new Document();
            int num = 1;
            boolean addingNew = true;
            while (addingNew){

                System.out.println("Document "+num +" :\n");
                document.setId(UniqueCodeGenerator.code());

                System.out.print("-> Title : ");
                String doc_title = scanner.nextLine();
                PrintStatement.validateNameStatement(doc_title, "Title");
                document.setTitle(doc_title);

                System.out.print("-> Price  : ");
                String doc_cost = scanner.nextLine();
                PrintStatement.validateNumberStatement(doc_cost, "Price");
                document.setPrice(Float.parseFloat(doc_cost));

                System.out.print("-> Refund Rate  : ");
                String doc_refund = scanner.nextLine();
                PrintStatement.validateNumberStatement(doc_refund, "Refund Rate");
                document.setRefund_rate(Float.parseFloat(doc_refund));

                document.setCreatedAt(new Date(System.currentTimeMillis()));
                document.setFolder(folder);

                documents.add(document);

                System.out.println("To add  new document click any key , Enter on 0 ta exit .");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
        return documents;
    }
    public List<ADocument> addMedicines(Folder folder){
        List<ADocument> medicines = new ArrayList<>();
        System.out.println("To add Medicines click any key , Enter on 0 ta exit .");
        String  med_option  = scanner.nextLine();
       if(!med_option.equals("0")) {

            Medicine medicine = new Medicine();

            int num = 1;
            boolean addingNew = true;
            while (addingNew){
                java.sql.Date createdAt = new java.sql.Date(System.currentTimeMillis());
                System.out.println("Medicine "+num +" :\n");
                String med_id = UniqueCodeGenerator.code();
                medicine.setId(med_id);

                System.out.print("-> Name : ");
                String med_name = scanner.nextLine();
                PrintStatement.validateNameStatement(med_name , "Name");
                medicine.setTitle(med_name);

                System.out.print("-> Price  : ");
                String med_price = scanner.nextLine();
                PrintStatement.validateNumberStatement(med_price, "Price");
                medicine.setPrice(Float.parseFloat(med_price));

                System.out.print("-> Refund Availability  : ");
                System.out.println("1 -> Yes");
                System.out.println("2 -> No");
                String med_refund_av = scanner.nextLine();

                if(!(med_refund_av.equals("1") || med_refund_av.equals("2"))){
                    System.out.println("\nInvalid choice, choose one of the following : ");

                    boolean notValid = true;
                    while (notValid){
                        System.out.print("-> Refund Availability  : ");
                        System.out.println("1 -> Yes");
                        System.out.println("2 -> No");
                        med_refund_av = scanner.nextLine();

                        if(med_refund_av.equals("1") || med_refund_av.equals("2")){
                            notValid = false;
                        }else{
                            System.out.println("\nInvalid choice, choose one of the following : ");
                        }
                    }
                }else {
                    if (med_refund_av.equals("1")){
                        System.out.print("-> Refund Rate  : ");
                        String med_refund = scanner.nextLine();
                        PrintStatement.validateNumberStatement(med_refund, "Refund Rate");
                        medicine.setRefund_rate(Float.parseFloat(med_refund));
                    }else{
                        medicine.setStatus(DocumentStatus.NOT_ACCEPTED);
                    }
                }

                medicine.setCreatedAt(createdAt);
                medicine.setFolder(folder);

                medicines.add(medicine);

                System.out.println("To add  new medicines click any key , Enter on 0 ta exit .");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
       return medicines;
    }

    public List<ADocument> addRadios(Folder folder){
        List<ADocument> radios = new ArrayList<>();
        System.out.println("To add a Radio click any key , Enter on 0 ta exit .");
        String  radio_option  = scanner.nextLine();
        if(!radio_option.equals("0")) {
            Radio radio = new Radio();
            int num = 1;

            boolean addingNew = true;
            while (addingNew){
                System.out.println("Radio "+num +" :\n");
                String med_id = UniqueCodeGenerator.code();
                radio.setId(med_id);

                System.out.print("-> Name : ");
                String med_name = scanner.nextLine();
                PrintStatement.validateNameStatement(med_name , "Name");
                radio.setTitle(med_name);

                System.out.print("-> Price  : ");
                String med_price = scanner.nextLine();
                PrintStatement.validateNumberStatement(med_price, "Price");
                radio.setPrice(Float.parseFloat(med_price));

                System.out.print("-> Refund Availability  : ");
                System.out.println("1 -> Yes");
                System.out.println("2 -> No");
                String med_refund_av = scanner.nextLine();

                if(!(med_refund_av.equals("1") || med_refund_av.equals("2"))){
                    System.out.println("\nInvalid choice, choose one of the following : ");

                    boolean notValid = true;
                    while (notValid){
                        System.out.print("-> Refund Availability  : ");
                        System.out.println("1 -> Yes");
                        System.out.println("2 -> No");
                        med_refund_av = scanner.nextLine();

                        if(med_refund_av.equals("1") || med_refund_av.equals("2")){
                            notValid = false;
                        }else{
                            System.out.println("\nInvalid choice, choose one of the following : ");
                        }
                    }
                }else {
                    if (med_refund_av.equals("1")){
                        System.out.print("-> Refund Rate  : ");
                        String med_refund = scanner.nextLine();
                        PrintStatement.validateNumberStatement(med_refund, "Refund Rate");
                        radio.setRefund_rate(Float.parseFloat(med_refund));
                    }else{
                        radio.setStatus(DocumentStatus.NOT_ACCEPTED);
                    }
                }

                radio.setCreatedAt(new Date(System.currentTimeMillis()));
                radio.setFolder(folder);

                radios.add(radio);

                System.out.println("To add a new Radios click any key , Enter on 0 ta exit .");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
        return radios;
    }

    public List<ADocument> addScanners(Folder folder){
        List<ADocument> scanners = new ArrayList<>();
        System.out.println("To add a Scanner click any key , Enter on 0 ta exit .");
        String  radio_option  = scanner.nextLine();
        if(!radio_option.equals("0")) {
            Scanner scanner1 = new Scanner();
            int num = 1;

            boolean addingNew = true;
            while (addingNew){
                System.out.println("Scanner "+num +" :\n");
                String med_id = UniqueCodeGenerator.code();
                scanner1.setId(med_id);

                System.out.print("-> Name : ");
                String med_name = scanner.nextLine();
                PrintStatement.validateNameStatement(med_name , "Name");
                scanner1.setTitle(med_name);

                System.out.print("-> Price  : ");
                String med_price = scanner.nextLine();
                PrintStatement.validateNumberStatement(med_price, "Price");
                scanner1.setPrice(Float.parseFloat(med_price));

                System.out.print("-> Refund Availability  : ");
                System.out.println("1 -> Yes");
                System.out.println("2 -> No");
                String med_refund_av = scanner.nextLine();

                if(!(med_refund_av.equals("1") || med_refund_av.equals("2"))){
                    System.out.println("\nInvalid choice, choose one of the following : ");

                    boolean notValid = true;
                    while (notValid){
                        System.out.print("-> Refund Availability  : ");
                        System.out.println("1 -> Yes");
                        System.out.println("2 -> No");
                        med_refund_av = scanner.nextLine();

                        if(med_refund_av.equals("1") || med_refund_av.equals("2")){
                            notValid = false;
                        }else{
                            System.out.println("\nInvalid choice, choose one of the following : ");
                        }
                    }
                }else {
                    if (med_refund_av.equals("1")){
                        System.out.print("-> Refund Rate  : ");
                        String med_refund = scanner.nextLine();
                        PrintStatement.validateNumberStatement(med_refund, "Refund Rate");
                        scanner1.setRefund_rate(Float.parseFloat(med_refund));
                    }else{
                        scanner1.setStatus(DocumentStatus.NOT_ACCEPTED);
                    }
                }

                scanner1.setCreatedAt(new Date(System.currentTimeMillis()));
                scanner1.setFolder(folder);

                scanners.add(scanner1);

                System.out.println("To add a new Scanner click any key , Enter on 0 ta exit .");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
        return scanners;
    }


}
