package org.macnss.dao;

import org.macnss.entity.Folder;

import java.util.Date;

public interface IFolderDAO extends DAO<Folder> {
    final String folderId = "folderId";
    final String folder_name = "folder_name";
    final String depositDate = "depositDate";
    final String folder_status = "folder_status";
    final String return_price = "return_price";
    Folder insert(Folder folder,String patientMatricule);

}
