package com.junggam.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "AUTH_TB")
public class AuthVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @OneToOne(targetEntity = UserVO.class)
    @JoinColumn(name = "user_idx")
    UserVO user;
}
