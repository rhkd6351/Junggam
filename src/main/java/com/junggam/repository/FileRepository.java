package com.junggam.repository;


import com.junggam.domain.FileVO;
import com.junggam.domain.ImageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileVO, Long> {

    Optional<FileVO> getByName(String name);


}
