package com.junggam.service;

import com.junggam.domain.BoardVO;
import com.junggam.domain.ImageVO;
import com.junggam.repository.BoardRepository;
import com.junggam.repository.ImageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public ImageVO getByName(String name) throws NotFoundException{
        Optional<ImageVO> image = imageRepository.getByName(name);
        if(image.isEmpty())
            throw new NotFoundException("Invalid Image Name");
        else
            return image.get();
    }

    public ImageVO save(ImageVO image){
        ImageVO savedImage = imageRepository.saveAndFlush(image);
        return savedImage;
    }



}
