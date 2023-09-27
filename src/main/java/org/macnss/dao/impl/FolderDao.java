package org.macnss.dao.impl;

import org.macnss.Enum.FolderStatus;
import org.macnss.dao.IFolderDAO;
import org.macnss.entity.Folder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolderDao implements IFolderDAO {

    @Override
    public Folder insert(Folder folder) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Folder (folder_id,folder_name,depositDate,folderStatus,return_price) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1,folder.getId());
            preparedStatement.setString(2,folder.getFolder_name());
            preparedStatement.setDate(3, (Date) folder.getDepositDate());
            preparedStatement.setString(4, String.valueOf(folder.getFolderStatus()));
            preparedStatement.setFloat(5,folder.getReturn_price());
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
        Folder folder = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Folder where folder_id = ?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String folder_id = resultSet.getString("folder_id");
                String folder_name = resultSet.getString("folder_name");
                Date depositDate = resultSet.getDate("depositDate");
                FolderStatus folder_status  = FolderStatus.valueOf(resultSet.getString("folderStatus"));
                Float returnPrice = resultSet.getFloat("return_price");
                folder = new Folder(folder_id,folder_name,depositDate,folder_status,returnPrice);
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
                String folder_id = resultSet.getString("folder_id");
                String folder_name = resultSet.getString("folder_name");
                Date depositDate = resultSet.getDate("depositDate");
                FolderStatus folder_status = FolderStatus.valueOf(resultSet.getString("folderStatus"));
                Float returnPrice = resultSet.getFloat("return_price");
                Folder folder = new Folder(folder_id,folder_name,depositDate,folder_status,returnPrice);
                folders.add(folder);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return folders;
    }
}
