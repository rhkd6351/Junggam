package com.junggam.controller;


import com.junggam.domain.ImageVO;
import com.junggam.dto.MessageDTO;
import com.junggam.service.ImageService;
import com.junggam.util.FileUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.NotSupportedException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class ImageController {


    ImageService imageService;
    FileUtil fileUtil;
    private final String serverUri;

    public ImageController(ImageService imageService, FileUtil fileUtil, @Value("${server.uri}")String serverUri) {
        this.imageService = imageService;
        this.fileUtil = fileUtil;
        this.serverUri = serverUri;
    }


    @GetMapping(path = "/img/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageByName(@PathVariable(value = "name") String name) throws NotFoundException, IOException {

        ImageVO image = imageService.getByName(name);
        byte[] imageByte = fileUtil.getImageByteFromImageVO(image);

        return ResponseEntity.ok(imageByte);
    }

    @PostMapping("/img")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageDTO> savePostImage(
            @RequestParam("img") MultipartFile mf) throws NotSupportedException, IOException {

        int i = mf.getOriginalFilename().lastIndexOf(".");
        String extension = mf.getOriginalFilename().substring(i);

        //파일 확장자 제한
        if(!(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".gif")))
            throw new NotSupportedException("not supported extension : " + extension);

        //파일 용량 제한
        if(mf.getSize() > (20 * 1024 * 1024)) //20메가 용량제한
            throw new NotSupportedException("exceed supported file size");

        UUID saveName = UUID.randomUUID(); //중복될 가능성 = 집에서 자다가 트럭에 받쳐서 사망할 가능성, 나중에 수정 필요..? 고려해볼것

        fileUtil.saveFile(mf, saveName + extension, "/board/post");

        ImageVO image = null;

        try {
            image = imageService.getByName(saveName.toString());
            image.setSaveName(saveName + extension);
            image.setSize(mf.getSize());
            image.setOriginalName(mf.getOriginalFilename());
            image.setUploadPath("/board/post");
        }catch (NotFoundException e) {
            image = ImageVO.builder()
                    .name(saveName.toString())
                    .saveName(saveName + extension)
                    .originalName(mf.getOriginalFilename())
                    .size(mf.getSize())
                    .uploadPath("/board/post")
                    .build();
        }finally {
            imageService.save(image);
        }


        return ResponseEntity.ok(new MessageDTO(serverUri + "/api/img/" + image.getName()));
    }

}
