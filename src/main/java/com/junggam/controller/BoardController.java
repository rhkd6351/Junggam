package com.junggam.controller;


import com.junggam.domain.BoardVO;
import com.junggam.dto.BoardDTO;
import com.junggam.service.BoardService;
import com.junggam.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    BoardService boardService;
    ObjectConverter objectConverter = new ObjectConverter();

    @GetMapping("/boards")
    public ResponseEntity<List<BoardDTO>> getBoardList(){
        List<BoardVO> boardVOs = boardService.getBoardList();
        List<BoardDTO> boardDTOs = boardVOs.stream().map(objectConverter::BoardVOToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(boardDTOs);
    }
}


















