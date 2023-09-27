package org.macnss.dao;

import org.macnss.Database.Database;

import java.sql.Connection;

public interface IDocumentDAO {

    final Connection connection = Database.getConnection();

    public double calculateRefundAmount();

}
