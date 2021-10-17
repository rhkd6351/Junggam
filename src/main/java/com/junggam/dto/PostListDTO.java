package com.junggam.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostListDTO {


    Integer currentPage;

    Integer totalPage;


    List<PostDTO> posts;
}

