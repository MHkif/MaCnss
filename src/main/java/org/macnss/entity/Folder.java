package org.macnss.entity;

import org.macnss.Enum.FolderStatus;

import java.util.Date;

public class Folder {
    private String id;
    private String folder_name;
    private Date depositDate;
    private FolderStatus folderStatus;
    private Patient patient;
    private Float return_price;
    private Agent agent;



  public Folder(){

  }

    public Folder(String id, String folder_name, Date depositDate, FolderStatus folderStatus, Patient patient, Float return_price, Agent agent) {
        this.id = id;
        this.folder_name = folder_name;
        this.depositDate = depositDate;
        this.folderStatus = folderStatus;
        this.patient = patient;
        this.return_price = return_price;
        this.agent = agent;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public float getReturn_price() {
        return return_price;
    }

    public void setReturn_price(Float return_price) {
        this.return_price = return_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public FolderStatus getFolderStatus() {
        return folderStatus;
    }

    public void setFolderStatus(FolderStatus folderStatus) {
        this.folderStatus = folderStatus;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", depositDate=" + depositDate +
                ", folderStatus=" + folderStatus +
                '}';
    }
}
