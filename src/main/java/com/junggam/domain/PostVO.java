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
@Table(name = "USER_TB")
public class PostVO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column
    String title;

    @Lob
    String content;

    @Column(name = "reg_date")
    @CreationTimestamp
    LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name="board_idx")
    BoardVO board;

    @ManyToOne
    @JoinColumn(name="user_idx")
    UserVO user;
}

