package com.junggam.controller;


import com.junggam.domain.BoardVO;
import com.junggam.domain.UserVO;
import com.junggam.dto.BoardDTO;
import com.junggam.service.BoardService;
import com.junggam.service.UserService;
import com.junggam.util.ObjectConverter;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    ObjectConverter oc = new ObjectConverter();

    @GetMapping("/boards")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BoardDTO>> getBoardList(){
        List<BoardVO> boardVOs = boardService.getBoardList();
        List<BoardDTO> boardDTOs = boardVOs.stream().map(oc::boardVOToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(boardDTOs);
    }

    @GetMapping("/board/{board-idx}")
    public ResponseEntity<BoardDTO> getBoardByIdx(
            @PathVariable(value = "board-idx") Long boardIdx) throws NotFoundException {

        BoardVO boardVO = boardService.getBoardByIdx(boardIdx);
        return ResponseEntity.ok(oc.boardVOToDTO(boardVO));
    }
}


















