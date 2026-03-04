package com.project.expence_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.expence_tracker.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


	 Optional<UserInfo> findByEmail(String email);

	    Optional<UserInfo> findByPhoneNumber(String phoneNumber);

	    Optional<UserInfo> findByEmailOrPhoneNumber(String email, String phoneNumber);

	    boolean existsByEmail(String email);

	    boolean existsByPhoneNumber(String phoneNumber);
}
