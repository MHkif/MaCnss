package org.macnss.entity;

import org.macnss.Enum.RadiosType;
import org.macnss.interfaces.IDocument;

import java.util.Date;

public class Radios {
    private int id;
    private Date radiosDate;
    private RadiosType radiosType;
    private String results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRadiosDate() {
        return radiosDate;
    }

    public void setRadiosDate(Date radiosDate) {
        this.radiosDate = radiosDate;
    }

    public RadiosType getRadiosType() {
        return radiosType;
    }

    public void setRadiosType(RadiosType radiosType) {
        this.radiosType = radiosType;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }


}
