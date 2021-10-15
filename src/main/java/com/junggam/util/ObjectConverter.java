package com.junggam.util;

import com.junggam.domain.BoardVO;
import com.junggam.dto.BoardDTO;

public class ObjectConverter {

    public BoardDTO BoardVOToDTO(BoardVO vo){
        return BoardDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .description(vo.getDescription())
                .build();
    }
}
