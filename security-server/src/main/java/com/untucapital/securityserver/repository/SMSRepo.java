package com.untucapital.securityserver.repository;

import com.untucapital.securityserver.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSRepo extends JpaRepository<Sms, String> {
}
