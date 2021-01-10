package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.PhotoDao;
import com.adianest.AdianestPaymentApp.model.Photo;
import com.adianest.AdianestPaymentApp.parameter.UserParameter;
import com.adianest.AdianestPaymentApp.service.IMediaHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class MediaHandlerServiceImpl implements IMediaHandler {

    private static final Logger log = LogManager.getLogger(MediaHandlerServiceImpl.class);

    @Autowired
    UserParameter userParameter;

    @Autowired
    PhotoDao photoDao;

    @Override
    public String uploadFile(MultipartFile file, String idUser) {
        String path = getPathFileByIdUser(idUser);

        StringBuilder fileName = new StringBuilder();
        fileName.append(idUser);
        fileName.append(".");
        fileName.append(FilenameUtils.getExtension(file.getOriginalFilename()));
        fileName.insert(0, path);
        try {
            FileUtils.writeByteArrayToFile(new File(fileName.toString()), file.getBytes());
            return fileName.toString();
        } catch (IOException e) {
            log.error("Error uploadFile(): {}", e);
        }

        return null;
    }

    @Override
    public String getPathFileByIdUser(String idUser) {

        StringBuilder path = new StringBuilder();
        path.append(".");
        path.append(File.separator);
        path.append(userParameter.getRootDirectory());
        path.append(File.separator);
        path.append(userParameter.getImgDirectoryName());
        path.append(File.separator);
        path.append(idUser);
        path.append(File.separator);

        return path.toString();
    }

    @Override
    public byte[] getPhoto(String idPhoto) {
        Photo p = photoDao.findById(Integer.parseInt(idPhoto)).orElse(null);

        if (p != null) {
            File file = new File(p.getPathFile());

            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                log.error("getFile() Error processing file: {}", e);
            }
        }

        return null;
    }
}
