package com.junggam.service;


import com.junggam.domain.BoardVO;
import com.junggam.domain.PostVO;
import com.junggam.repository.BoardRepository;
import com.junggam.repository.PostRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {


    @Autowired
    PostRepository postRepository;

    @Autowired
    BoardService boardService;

    public List<PostVO> getPostListByBoardIdx(Long boardIdx) throws NotFoundException {

        BoardVO board = boardService.getBoardByIdx(boardIdx); // board 존재 확인 -> boardService 내부에서 예외처리
        return postRepository.findByBoardIdx(board.getIdx());
    }
}
