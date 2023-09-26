package org.macnss.entity;

import org.macnss.Enum.ScannerType;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> MACNSS-1
=======
>>>>>>> MACNSS-2
=======
>>>>>>> MACNSS-3
=======
>>>>>>> MACNSS-6

import java.util.Date;


public class Scanner  {

    private int id;
    private Date scannerDate;
    private ScannerType scannerType;
    private String results;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getScannerDate() {
        return scannerDate;
    }

    public void setScannerDate(Date scannerDate) {
        this.scannerDate = scannerDate;
    }

    public ScannerType getScannerType() {
        return scannerType;
    }

    public void setScannerType(ScannerType scannerType) {
        this.scannerType = scannerType;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "id=" + id +
                ", scannerDate=" + scannerDate +
                ", scannerType=" + scannerType +
                ", results='" + results + '\'' +
                '}';
    }

}
