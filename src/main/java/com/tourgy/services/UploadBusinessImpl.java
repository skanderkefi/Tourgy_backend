package com.tourgy.services;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetMetadataErrorException;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.LookupError;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.users.FullAccount;
//import com.quefi.dropboxservice.config.DropBoxConfig;

@Service
public class UploadBusinessImpl implements UploadBusiness{

private DbxRequestConfig config;
private  DbxClientV2 client;
Logger logger = LoggerFactory.getLogger(UploadBusinessImpl.class);
@Autowired
DropBoxConfig dropbox;
@Autowired
Utilities utilities;

@Override
public String uploadFile(MultipartFile file) throws CreateFolderErrorException, FileNotFoundException, IOException, DbxException {

this.initClient();

createFolderIfNotExist(dropbox.getPathFolder(),client);
return uploadFile(client,dropbox.getPathFolder(),file);


}



public void getAccount( DbxClientV2 client ) throws DbxApiException, DbxException
   {
    FullAccount account = client.users().getCurrentAccount();
    logger.info("NAME:",account.getName());
   }
   public  String uploadFile( DbxClientV2 client ,String path,MultipartFile file) throws FileNotFoundException, IOException, CreateFolderErrorException, DbxException
   {
    String fileName= utilities.generateAleatoireString();
    client.files().uploadBuilder(path+"/"+fileName) .uploadAndFinish(file.getInputStream());
           SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(path+"/"+fileName);
           return sharedLinkMetadata.getUrl();
       
   }
   
   public  void createFolderIfNotExist(String nameFolder,DbxClientV2 dbxClient)
   {
                     
               try {
dbxClient.files().getMetadata(nameFolder);
}
               catch (GetMetadataErrorException e)
               {
            	   if (e.errorValue.isPath())
                   {
                       LookupError le = e.errorValue.getPathValue();
                       if (le.isNotFound())
                       {
                        logger.info("PATH DOES NOT EXIST ON FOLDER",nameFolder);
                           try
                           {
                               dbxClient.files().createFolderV2(nameFolder);
                           }
                           catch (CreateFolderErrorException e1)
                           {
                           
                               e1.printStackTrace();
                           }
                           catch (DbxException e1)
                           {
                           
                               e1.printStackTrace();
                           }
                       }
                   }
               }
               catch (DbxException e) {

e.printStackTrace();
}
           }
   
   
   public static void getFolders(DbxClientV2 client,String folderName ) throws ListFolderErrorException, DbxException
   {
    ListFolderResult result = client.files().listFolder("" );
    for( Metadata metadata : result.getEntries() )
     {
   
    String sName        = metadata.getPathLower();
       FileMetadata fileMetadata = (FileMetadata) metadata;
       
       
     }
   }
   

public void initClient()
{
        this.config = DbxRequestConfig.newBuilder("dropbox/platformjvm").build();
        this.client = new DbxClientV2(config, dropbox.getToken());

}




}
