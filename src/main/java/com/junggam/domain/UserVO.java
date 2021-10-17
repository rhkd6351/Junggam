package com.junggam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "USER_TB")
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

    @JsonIgnore
    @Column(name = "activated")
    boolean activated;

    @ManyToOne
    @JoinColumn(name = "auth_idx")
    AuthVO auth;
}