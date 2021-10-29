package com.junggam.controller;


import com.junggam.domain.FileVO;
import com.junggam.dto.MessageDTO;
import com.junggam.service.FileService;
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
public class FileController {

    FileService fileService;
    FileUtil fileUtil;
    private final String serverUri;

    public FileController(FileUtil fileUtil, @Value("${server.uri}") String serverUri, FileService fileService) {
        this.fileUtil = fileUtil;
        this.serverUri = serverUri;
        this.fileService = fileService;
    }


    @GetMapping(path = "/file/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getFileByName(@PathVariable(value = "name") String name) throws NotFoundException, IOException {

        FileVO file = fileService.getByName(name);
        byte[] fileByte = fileUtil.getByteFromFileVO(file);

        return ResponseEntity.ok(fileByte);
    }

    @PostMapping("/file")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageDTO> savePostImage(
            @RequestParam("file") MultipartFile mf) throws NotSupportedException, IOException {

        int i = mf.getOriginalFilename().lastIndexOf(".");
        String extension = mf.getOriginalFilename().substring(i);

//        //파일 확장자 제한
//        if(!(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".gif")))
//            throw new NotSupportedException("not supported extension : " + extension);

        //파일 용량 제한
        if (mf.getSize() > (100 * 1024 * 1024)) //100메가 용량제한
            throw new NotSupportedException("exceed supported file size");

        UUID saveName = UUID.randomUUID(); //중복될 가능성 = 집에서 자다가 트럭에 받쳐서 사망할 가능성, 나중에 수정 필요..? 고려해볼것

        fileUtil.saveFile(mf, saveName + extension, "/board/post");

        FileVO file = null;

        try {
            file = fileService.getByName(saveName.toString());
            file.setSaveName(saveName + extension);
            file.setSize(mf.getSize());
            file.setOriginalName(mf.getOriginalFilename());
            file.setUploadPath("/board/post");
        } catch (NotFoundException e) {
            file = FileVO.builder()
                    .name(saveName.toString())
                    .saveName(saveName + extension)
                    .originalName(mf.getOriginalFilename())
                    .size(mf.getSize())
                    .uploadPath("/board/post")
                    .build();
        } finally {
            fileService.save(file);
        }


        return ResponseEntity.ok(new MessageDTO(serverUri + "/api/file/" + file.getName()));
    }

}
