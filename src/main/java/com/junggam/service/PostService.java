package com.junggam.service;


import com.junggam.domain.BoardVO;
import com.junggam.domain.PostVO;
import com.junggam.repository.PostRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    PostRepository postRepository;

    @Autowired
    BoardService boardService;

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


}







