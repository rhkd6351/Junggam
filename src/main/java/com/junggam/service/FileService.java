package com.junggam.service;

import com.junggam.domain.FileVO;
import com.junggam.repository.FileRepository;
import com.junggam.repository.ImageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public FileVO getByName(String name) throws NotFoundException{
        Optional<FileVO> file = fileRepository.getByName(name);

        if(file.isEmpty())
            throw new NotFoundException("Invalid File Name");
        else
            return file.get();
    }

    public FileVO save(FileVO file){
        FileVO savedFile = fileRepository.saveAndFlush(file);
        return savedFile;
    }



}
