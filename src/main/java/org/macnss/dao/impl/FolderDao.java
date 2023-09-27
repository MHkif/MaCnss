package org.macnss.dao.impl;

import org.macnss.Enum.FolderStatus;
import org.macnss.dao.IFolderDAO;
import org.macnss.entity.Document;
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Folder (folder_id,folder_name,depositDate,folderStatus,matricule,agentId) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,folder.getId());
            preparedStatement.setString(2,folder.getFolder_name());
            preparedStatement.setDate(3, (Date) folder.getDepositDate());
            preparedStatement.setString(4, String.valueOf(folder.getFolderStatus()));
            preparedStatement.setString(5,folder.getPatient().getMatricule());
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
    public Document saveDocument(Document document){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Document (document_id,title,date_created,folder_id) VALUES(?,?,?,?)");
            preparedStatement.setString(1,document.getDocument_id());
            preparedStatement.setString(2,document.getTitle());
            preparedStatement.setDate(3, (Date) document.getDate_created());
            preparedStatement.setString(4,document.getFolder().getId());
            int row = preparedStatement.executeUpdate();
            if(row > 0){
                return document;
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
            preparedStatement.setString(1, String.valueOf(folder.getFolderStatus()));
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
                folder.setFolder_name(resultSet.getString("folder_name"));
                folder.setDepositDate(resultSet.getDate("depositDate"));
                folder.setFolderStatus(FolderStatus.valueOf(resultSet.getString("folderStatus")));
                folder.setReturn_price(resultSet.getFloat("return_price"));

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
                folder.setFolder_name(resultSet.getString("folder_name"));
                folder.setDepositDate(resultSet.getDate("depositDate"));
                folder.setFolderStatus(FolderStatus.valueOf(resultSet.getString("folderStatus")));
                folder.setReturn_price(resultSet.getFloat("return_price"));
                folders.add(folder);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return folders;
    }
}
