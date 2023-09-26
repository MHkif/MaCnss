package org.macnss.Services;

import org.macnss.dao.impl.AdminDAO;
import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();
    AgentDAO agentDAO = new AgentDAO();

    public boolean login(String email, String password) throws SQLException {
      Admin admin = adminDAO.login(email, password);
        return admin != null;
    }

    public Agent createAgent(Agent agent){
        if(agentDAO.insert(agent) != null){
            return agent;
        }else {
            return null;
        }
    }

    public Agent updateAgent(Agent agent){
        if(agentDAO.update(agent) != null){
            return agent;
        }else {
            return null;
        }
    }

    public Agent getAgent(String agentId){
        if(agentDAO.get(agentId) != null){
            return agentDAO.get(agentId);
        }else {
            return null;
        }

    }

    public List<Agent> getAllAgents(){
        List<Agent> agents = new ArrayList<Agent>();

        return agents;
    }

    public boolean deleteAgent(){
        Agent agent = new Agent();
        return true;
    }
}
