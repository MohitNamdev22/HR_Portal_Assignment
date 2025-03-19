package com.hrportal.hr_portal.service;

import com.hrportal.hr_portal.model.HrUser;
import com.hrportal.hr_portal.repository.HrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrUserService {

    @Autowired
    private HrUserRepository hrUserRepository;

    public HrUser authenticate(String email, String password) {
        HrUser user = hrUserRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
