package org.macnss.controllers;


import org.macnss.entity.Agent;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.Validator;

import java.util.Scanner;

public class Navigator extends Controller {

    private final AdminController adminController = new AdminController();
    private final AgentController agentController = new AgentController();

    public  void index(){

        PrintStatement.opening("MaCnss Application");
        try {

            boolean isRunning = true;

            while (isRunning){
                PrintStatement.options();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){

                    switch (Integer.parseInt(option)) {
                        case 0 -> isRunning = false;
                        case 1 -> adminController.login();
                        case 2 -> agentController.login();
                        case 3 -> this.folderHistory();
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


    private void folderHistory(){

    }
}
