package com.junggam.util;

import com.junggam.domain.AuthVO;
import com.junggam.domain.BoardVO;
import com.junggam.domain.PostVO;
import com.junggam.domain.UserVO;
import com.junggam.dto.AuthDTO;
import com.junggam.dto.BoardDTO;
import com.junggam.dto.PostDTO;
import com.junggam.dto.UserDTO;

public class ObjectConverter {

    public BoardDTO boardVOToDTO(BoardVO vo){
        return BoardDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .description(vo.getDescription())
                .build();
    }

    public PostDTO postVOToDTO(PostVO vo){
        return PostDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .content(vo.getContent())
                .regDate(vo.getRegDate())
                .board(this.boardVOToDTO(vo.getBoard()))
                .user(this.userVOToDTO(vo.getUser()))
                .build();
    }

    public UserDTO userVOToDTO(UserVO vo){
        return UserDTO.builder()
                .idx(vo.getIdx())
                .username(vo.getUsername())
                .password(null)
                .auth(this.authVOToDTO(vo.getAuth()))
                .build();
    }

    public AuthDTO authVOToDTO(AuthVO vo){
        return AuthDTO.builder()
                .idx(vo.getIdx())
                .build();
    }
}
