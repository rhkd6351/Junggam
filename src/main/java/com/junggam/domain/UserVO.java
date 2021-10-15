package com.junggam.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "POST_TB")
public class UserVO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column
    String username;

    @Column
    String password;

    @Column(name = "reg_date")
    @CreationTimestamp
    LocalDateTime regDate;

    @Column(name = "activated")
    boolean activated;

    @OneToOne(mappedBy = "user", orphanRemoval = false)
    AuthVO auth;
}