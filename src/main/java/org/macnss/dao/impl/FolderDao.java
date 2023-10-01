package org.macnss.dao.impl;

import org.macnss.Enum.FolderStatus;
import org.macnss.dao.IFolderDAO;
import org.macnss.entity.ADocument;
import org.macnss.entity.Folder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolderDao implements IFolderDAO {

    @Override
    public Folder insert(Folder folder,String patientMatricule) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Folder (folder_id,name,despositDate,status,matricule,agent_id) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,folder.getId());
            preparedStatement.setString(2,folder.getName());
            preparedStatement.setDate(3, (Date) folder.getDepositDate());
            preparedStatement.setString(4, String.valueOf(folder.getStatus()));
            preparedStatement.setString(5,folder.getPatient().getMatriculate());
//            preparedStatement.setFloat(6,folder.getReturn_price());
            preparedStatement.setString(6,folder.getAgent().getId());
            int row = preparedStatement.executeUpdate();
            if(row > 0){
                return folder;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ADocument saveDocument(ADocument ADocument){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Document (document_id,title,date_created,folder_id, price, taux) VALUES(?,?,?,?, ?,?)");
            preparedStatement.setString(1, ADocument.getId());
            preparedStatement.setString(2, ADocument.getTitle());
            preparedStatement.setDate(3, (Date) ADocument.getCreatedAt());
            preparedStatement.setString(4, ADocument.getFolder().getId());
            preparedStatement.setFloat(5, ADocument.getPrice());
            preparedStatement.setFloat(6, ADocument.getRefund_rate());
            int row = preparedStatement.executeUpdate();
            if(row > 0){
                return ADocument;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Folder insert(Folder folder){
        return null;
    }

    @Override
    public Folder update(Folder folder) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Folder SET folderStatus = ? where folder_id = ?");
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
    public boolean delete(String slag) throws SQLException {
        return false;
    }


    @Override
    public Folder get(String id) throws SQLException {
        Folder folder = new Folder();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Folder where folder_id = ?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                folder.setId(resultSet.getString("folder_id"));
                folder.setName(resultSet.getString("name"));
                folder.setDepositDate(resultSet.getDate("despositDate"));
                folder.setStatus(FolderStatus.valueOf(resultSet.getString("status")));
                folder.setTotal_refund(resultSet.getFloat("return_price"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return folder;
    }

    @Override
    public List<Folder> getAll() throws SQLException {
        List<Folder> folders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Folder");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Folder folder = new Folder();
                folder.setId(resultSet.getString("folder_id"));
                folder.setName(resultSet.getString("name"));
                folder.setDepositDate(resultSet.getDate("despositDate"));
                folder.setStatus(FolderStatus.valueOf(resultSet.getString("status")));
                folder.setTotal_refund(resultSet.getFloat("return_price"));
                folders.add(folder);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return folders;
    }
}
