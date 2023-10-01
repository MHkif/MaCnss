package org.macnss.dao;

import org.macnss.Database.Database;
import org.macnss.entity.ADocument;

import java.sql.Connection;
import java.util.List;

public interface IDocumentDAO {
    final Connection connection = Database.getConnection();
    final String table = "documents";

    final String id = "id";

    final String title = "title";

    final String type = "type";
    final String createdAt = "createdAt";

    final String price = "price";

    final String refund_rate = "refund_rate";


    public ADocument insert(ADocument ADocument);

    public float calculateRefundAmount(ADocument ADocument);


}
