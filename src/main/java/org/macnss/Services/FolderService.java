package org.macnss.Services;

import org.macnss.dao.impl.FolderDao;
import org.macnss.entity.Folder;

import java.sql.SQLException;
import java.util.List;

public class FolderService {
    private FolderDao folderDao;

    public FolderService(FolderDao folderDao) {
        this.folderDao = folderDao;
    }
    public Folder createFolder(Folder folder){
        if (folderDao.insert(folder) != null){
            return folder;
        }else {
            return null;
        }
    }
    public Folder updateFolder(Folder folder){
        if (folderDao.update(folder) != null){
            return folder;
        }else {
            return null;
        }
    }
    public List<Folder> getAllFolders() throws SQLException {

        List<Folder> folders = folderDao.getAll();
        return folders;
    }
    public Folder getOneFolder(String folderId) throws SQLException {
        if (folderDao.get(folderId) != null){
            return folderDao.get(folderId);
        }else  {
            return  null;
        }
    }
}
