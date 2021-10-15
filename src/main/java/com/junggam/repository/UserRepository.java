package com.junggam.repository;


import com.junggam.domain.PostVO;
import com.junggam.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {


}
