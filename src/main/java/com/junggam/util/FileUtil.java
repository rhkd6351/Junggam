package com.junggam.util;


import com.junggam.domain.FileVO;
import com.junggam.domain.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileUtil {

    private final String originPath;

    public FileUtil(@Value("${img.path}") String originPath) {
        this.originPath = originPath;
    }

    public byte[] getImageByteFromImageVO(ImageVO uvo)
            throws IOException {
        File file = new File(originPath + uvo.getUploadPath() +"/"+ uvo.getSaveName());
        byte[] byfile = null;

        //it can throw IOException
        byfile = Files.readAllBytes(file.toPath());

        return byfile;
    }

    public byte[] getByteFromFileVO(FileVO fvo)
            throws IOException {
        File file = new File(originPath + fvo.getUploadPath() +"/"+ fvo.getSaveName());
        byte[] byfile = null;

        //it can throw IOException
        byfile = Files.readAllBytes(file.toPath());

        return byfile;
    }

    public boolean saveFile(MultipartFile multipartFile, String name, String uploadPath) throws IOException {

        File file = new File(originPath + uploadPath + "/" + name);
        //it can throw IOException
        multipartFile.transferTo(file);
        return true;
    }
}
