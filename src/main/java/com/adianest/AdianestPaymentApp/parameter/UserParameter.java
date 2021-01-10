package com.adianest.AdianestPaymentApp.parameter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.user")
public class UserParameter {
    private String rootDirectory;
    private String imgDirectoryName;

    public String getRootDirectory() {
        return rootDirectory;
    }

    public String getImgDirectoryName() {
        return imgDirectoryName;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void setImgDirectoryName(String imgDirectoryName) {
        this.imgDirectoryName = imgDirectoryName;
    }
}
