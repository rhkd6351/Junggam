package com.junggam.dto;

import com.junggam.domain.BoardVO;
import com.junggam.domain.UserVO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {

    Long idx;

    String title;

    String content;

    LocalDateTime regDate;

    BoardDTO board;

    UserDTO user;
}

