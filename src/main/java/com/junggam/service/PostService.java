package com.junggam.service;


import com.junggam.domain.BoardVO;
import com.junggam.domain.PostVO;
import com.junggam.domain.UserVO;
import com.junggam.dto.PostDTO;
import com.junggam.repository.PostRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    PostRepository postRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    public List<PostVO> getPostListByBoardIdx(Long boardIdx) throws NotFoundException {

        BoardVO board = boardService.getBoardByIdx(boardIdx); // board 존재 확인 -> boardService 내부에서 예외처리
        return postRepository.getByBoardIdxOrderByIdxDesc(board.getIdx());
    }

    public Page<PostVO> getPostListByBoardIdx(Long boardIdx, Pageable pageable) throws NotFoundException {

        BoardVO board = boardService.getBoardByIdx(boardIdx);

        return postRepository.getByBoardIdxOrderByIdxDesc(board.getIdx(), pageable);
    }

    public PostVO getPostByPostIdx(Long postIdx) throws NotFoundException {

        Optional<PostVO> post = postRepository.findById(postIdx);

        if(post.isEmpty())
            throw new NotFoundException("Invalid Post Idx");
        else
            return post.get();
    }

    public PostVO save(PostDTO dto) throws AuthException, NotFoundException {

        PostVO postVO;

        if(dto.getIdx() != null && dto.getIdx() >= 0){
            postVO = postRepository.getById(dto.getIdx());
            postVO.setContent(dto.getContent());
            postVO.setTitle(dto.getTitle());
        }else {

            UserVO user = userService.getMyUserWithAuthorities();
            BoardVO board = boardService.getBoardByIdx(dto.getBoard().getIdx());

            postVO = PostVO.builder()
                    .board(board)
                    .user(user)
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .build();
        }

        return this.save(postVO);
    }


    public PostVO save(PostVO vo){
       return postRepository.save(vo);
    }

    public void remove(long idx) throws NotFoundException, AuthException {
        PostVO post = this.getPostByPostIdx(idx);
        UserVO user = userService.getMyUserWithAuthorities();
        UserVO postUser = post.getUser();
        if (postUser != user)
            throw new AuthException("본인이 게시한 글이 아닙니다.");

        postRepository.delete(post);
    }



}







