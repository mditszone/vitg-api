package com.vitg.serviceimpl;

import com.vitg.config.CustomPropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service// Note the file name with the extension unlike a property file. Also, it's not the `application.yml` file.
public class FileStorageServiceImpl {

    private final Path fileStorageLocation;


    public FileStorageServiceImpl() {
        this.fileStorageLocation = Paths.get("./uploads/files")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String[] fileNameParts = fileName.split("\\.");

        return fileNameParts[fileNameParts.length - 1];
    }


    public String storeFile(File file) {
        // Normalize file name
        String fileName =
                new Date().getTime() + "-file." + getFileExtension(file.getName());

        // Check if the filename contains invalid characters
        if (fileName.contains("..")) {
            throw new RuntimeException(
                    "Sorry! Filename contains invalid path sequence " + fileName);
        }

        Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file., targetLocation, StandardCopyOption.REPLACE_EXISTING);
        File newfile = new File(targetLocation + "/" + fileName);
        return fileName;
    }
}