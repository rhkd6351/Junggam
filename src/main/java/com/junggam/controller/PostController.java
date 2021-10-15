package com.junggam.controller;


import com.junggam.domain.PostVO;
import com.junggam.dto.PostDTO;
import com.junggam.service.PostService;
import com.junggam.util.ObjectConverter;
import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    ObjectConverter oc = new ObjectConverter();

    @GetMapping(path = "/board/{board-idx}/posts")
    public ResponseEntity<List<PostDTO>> getPostListByBoardIdx(
            @PathVariable(name = "board-idx") Long boardIdx) throws NotFoundException {

        List<PostVO> posts = postService.getPostListByBoardIdx(boardIdx);

        List<PostDTO> postDTOs = posts.stream().map(oc::postVOToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(postDTOs);
    }



}
