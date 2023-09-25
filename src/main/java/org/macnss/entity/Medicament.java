package org.macnss.entity;

import org.macnss.Enum.MedicamentStatus;

public class Medicament {
    private int id;
    private String name;
    private MedicamentStatus medicamentStatus;
    private double price;
    private double taux;

    public Medicament(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicamentStatus getMedicamentStatus() {
        return medicamentStatus;
    }

    public void setMedicamentStatus(MedicamentStatus medicamentStatus) {
        this.medicamentStatus = medicamentStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", medicamentStatus=" + medicamentStatus +
                ", price=" + price +
                ", taux=" + taux +
                '}';
    }
}
