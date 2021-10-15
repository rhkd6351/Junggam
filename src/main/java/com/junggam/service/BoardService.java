package com.junggam.service;

import com.junggam.domain.BoardVO;
import com.junggam.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<BoardVO> getBoardList(){
        return boardRepository.findAll();
    }

}
