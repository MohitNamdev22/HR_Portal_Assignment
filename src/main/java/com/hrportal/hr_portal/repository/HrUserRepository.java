package com.hrportal.hr_portal.repository;

import com.hrportal.hr_portal.model.HrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrUserRepository extends JpaRepository<HrUser, Long> {
    HrUser findByEmail(String email);
}