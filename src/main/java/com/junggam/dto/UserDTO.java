package com.junggam.dto;

import com.junggam.domain.AuthVO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    Long idx;

    String username;

    String password;

    LocalDateTime regDate;

    AuthDTO auth;
}