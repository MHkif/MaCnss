package org.macnss.dao;

import org.macnss.entity.Agent;

import java.sql.SQLException;

public interface IAgentDAO extends DAO<Agent>{
    final String id = "id";
    final String name = "name";
    final String email = "email";
    final String password = "password";
    final String verificationCode = "verificationCode";

    public Agent login(String email, String password) throws SQLException;
}
