package org.macnss.dao.impl;

import org.macnss.dao.IDocumentDAO;
import org.macnss.entity.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentDao implements IDocumentDAO {
    org.macnss.entity.Document document = new org.macnss.entity.Document();

    @Override
    public double calculateRefundAmount() {

        return 0;
    }
    public Document getOne(String id){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Medicament WHERE medicament_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                document.setTitle(resultSet.getString("title"));
                document.setDate_created(resultSet.getDate("date_created"));
            }else {
                return null;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return document;
    }
}
