package com.junggam.controller;


import com.junggam.domain.PostVO;
import com.junggam.dto.BoardDTO;
import com.junggam.dto.MessageDTO;
import com.junggam.dto.PostDTO;
import com.junggam.dto.PostListDTO;
import com.junggam.service.PostService;
import com.junggam.util.ObjectConverter;
import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {
    static final int POST_PER_PAGE = 10;

    @Autowired
    PostService postService;

    ObjectConverter oc = new ObjectConverter();

    @GetMapping(path = "/board/{board-idx}/posts")
    public ResponseEntity<PostListDTO> getPostListByBoardIdx(
            @PathVariable(name = "board-idx") Long boardIdx,
            @RequestParam(name = "pno", required = false) Integer pno) throws Exception {

        List<PostVO> posts;
        PostListDTO postListDTO = new PostListDTO();

        if (pno == null) {
            posts = postService.getPostListByBoardIdx(boardIdx);
            postListDTO.setTotalPage(1);
            postListDTO.setCurrentPage(1);

        } else if (0 < pno && pno < 9999) {
            PageRequest pageRequest = PageRequest.of(pno - 1, POST_PER_PAGE);
            Page<PostVO> postPage = postService.getPostListByBoardIdx(boardIdx, pageRequest);
            posts = postPage.getContent();

            postListDTO.setTotalPage(postPage.getTotalPages());
            postListDTO.setCurrentPage(pno);
        } else {
            throw new Exception("Invalid pno"); //TODO Exception 종류 수정
        }

        List<PostDTO> postDTOs = posts.stream().map(oc::postVOToDTO).collect(Collectors.toList());
        postListDTO.setPosts(postDTOs);
        return ResponseEntity.ok(postListDTO);
    }


    @GetMapping(path = "/board/post/{post-idx}")
    public ResponseEntity<PostDTO> getPostByPostIdx(
            @PathVariable(name = "post-idx") Long postIdx) throws NotFoundException {

        PostVO post = postService.getPostByPostIdx(postIdx);

        return ResponseEntity.ok(oc.postVOToDTO(post));
    }

    @PostMapping(path = "/board/{board-idx}/post")
    public ResponseEntity<PostDTO> savePost(@PathVariable(name = "board-idx") Long boardIdx,
                                            @RequestBody PostDTO postDTO
    ) throws AuthException, NotFoundException {
        postDTO.setBoard(BoardDTO.builder().idx(boardIdx).build());
        PostVO savedPost = postService.save(postDTO);

        return ResponseEntity.ok(oc.postVOToDTO(savedPost));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/board/post/{post-idx}")
    public ResponseEntity<MessageDTO> deletePostByIdx(
            @PathVariable(name = "post-idx") Long postIdx) throws NotFoundException, AuthException {

        postService.remove(postIdx);
        return ResponseEntity.ok(MessageDTO.builder().message("delete success").build());
    }
}












