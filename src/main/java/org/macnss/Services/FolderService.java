package org.macnss.Services;

import org.macnss.dao.impl.DocumentDAO;
import org.macnss.dao.impl.FolderDao;
import org.macnss.entity.Folder;

import java.sql.SQLException;
import java.util.List;

public class FolderService {
    private final FolderDao folderDao = new FolderDao();
    private final DocumentDAO documentDao = new DocumentDAO();

    public Folder create(Folder folder){
        if (folderDao.insert(folder) != null){
            return folder;
        }else {
            return null;
        }
    }


    public double calculate(Folder folder){
        return 0;
    }
    public Folder updateFolder(Folder folder){
        if (folderDao.update(folder) != null){
            return folder;
        }else {
            return null;
        }
    }
    public List<Folder> getAll() throws SQLException {

        List<Folder> folders = folderDao.getAll();
        return folders;
    }
    public Folder get(String folderId) throws SQLException {
        if (folderDao.get(folderId) != null){
            return folderDao.get(folderId);
        }else  {
            return  null;
        }
    }


}
