package org.macnss.Services;

import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;

import java.sql.SQLException;

public class AgentService {

    AgentDAO agentDAO = new AgentDAO();

    public boolean login(String email, String password) throws SQLException {
        Agent agent = agentDAO.login(email, password);
        return agent != null;
    }
    public boolean create(Agent agent){
        return agentDAO.insert(agent) != null;
    }
}
