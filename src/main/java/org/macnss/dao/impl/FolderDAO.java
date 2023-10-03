package org.macnss.dao.impl;

import org.macnss.Enum.FolderStatus;
import org.macnss.dao.IFolderDAO;
import org.macnss.entity.ADocument;
import org.macnss.entity.Agent;
import org.macnss.entity.Folder;
import org.macnss.entity.Patient;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolderDAO implements IFolderDAO {

    @Override
    public Folder insert(Folder folder) {
        String sql = "INSERT INTO "+TABLE+"(id, name, despositAt, status, matriculate, total_refund, agent_id) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,folder.getId());
            preparedStatement.setString(2,folder.getName());
            preparedStatement.setDate(3, (Date) folder.getDepositDate());
            preparedStatement.setString(4, String.valueOf(folder.getStatus()));
            preparedStatement.setString(5,folder.getPatient().getMatriculate());
            preparedStatement.setFloat(6,folder.getTotal_refund());
            preparedStatement.setString(7,folder.getAgent().getId());
            int row = preparedStatement.executeUpdate();
            if(row > 0){
                return folder;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Folder update(Folder folder) {
        String sql = "UPDATE "+TABLE+" SET status = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(folder.getStatus()));
            preparedStatement.setString(2,folder.getId());
            int row = preparedStatement.executeUpdate();
            if (row > 0){
                return folder;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(String slag)  {
        return false;
    }


    @Override
    public Folder get(String id) {
        String sql = "SELECT * FROM "+TABLE+" where id = ?";
        Folder folder = new Folder();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                folder.setId(resultSet.getString(ID));
                folder.setName(resultSet.getString(NAME));
                folder.setDepositDate(resultSet.getDate(DEPOSIT_DATE));
                folder.setStatus(FolderStatus.valueOf(resultSet.getString(STATUS)));
                folder.setTotal_refund(resultSet.getFloat(TOTAL_REFUND));
                //folder.setPatient((Patient) resultSet.getObject(PATIENT_MATRICULATE));
                //folder.setAgent((Agent) resultSet.getObject(AGENT_ID));

            }
        }catch (SQLException e){
            new RuntimeException();
        }
        return folder;
    }

    @Override
    public List<Folder> getAll(){
        String sql = "SELECT * FROM "+TABLE;
        List<Folder> folders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Folder folder = new Folder();
                folder.setId(resultSet.getString(ID));
                folder.setName(resultSet.getString(NAME));
                folder.setDepositDate(resultSet.getDate(DEPOSIT_DATE));
                folder.setStatus(FolderStatus.valueOf(resultSet.getString(STATUS)));
                folder.setTotal_refund(resultSet.getFloat(TOTAL_REFUND));
                //folder.setPatient((Patient) resultSet.getObject(PATIENT_MATRICULATE));
                //folder.setAgent((Agent) resultSet.getObject(AGENT_ID));
                folders.add(folder);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return folders;
    }
}
