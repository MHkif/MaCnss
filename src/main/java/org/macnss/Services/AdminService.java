package org.macnss.Services;

import org.macnss.dao.impl.AdminDAO;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;

import java.sql.SQLException;

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();

    public boolean login(String email, String password) throws SQLException {
      Admin admin = adminDAO.login(email, password);
        return admin != null;
    }

    public void createAgent(){

    }
}
