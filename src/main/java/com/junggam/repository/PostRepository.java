package com.junggam.repository;


import com.junggam.domain.BoardVO;
import com.junggam.domain.PostVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostVO, Long> {

    public List<PostVO> findByBoardIdx(Long boardIdx);


}
