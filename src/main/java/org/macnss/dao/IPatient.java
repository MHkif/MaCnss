package org.macnss.dao;

import org.macnss.dao.DAO;
import org.macnss.entity.Patient;

public interface IPatient extends DAO<Patient> {

    final String table = "patients";
    final String matriculate_col = "matriculate";
    final String fullName = "fullName";

}
