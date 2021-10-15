package com.junggam.service;

import com.junggam.domain.BoardVO;
import com.junggam.repository.BoardRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<BoardVO> getBoardList(){
        return boardRepository.findAll();
    }

    public BoardVO getBoardByIdx(Long idx) throws NotFoundException {
        Optional<BoardVO> board = boardRepository.findById(idx);
        if(board.isEmpty())
            throw new NotFoundException("Invalid Board Idx");
        else
            return board.get();
    }

}
