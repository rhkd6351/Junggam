package com.junggam.repository;


import com.junggam.domain.AuthVO;
import com.junggam.domain.ImageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageVO, Long> {

    Optional<ImageVO> getByName(String name);


}
