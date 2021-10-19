package com.junggam.repository;


import com.junggam.domain.BoardVO;
import com.junggam.domain.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostVO, Long> {

    public List<PostVO> getByBoardIdxOrderByIdxDesc(Long boardIdx);

    public Page<PostVO> getByBoardIdxOrderByIdxDesc(Long boardIdx, Pageable pageable);

}
