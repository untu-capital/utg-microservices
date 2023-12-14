package com.example.notification.repository;


import com.example.notification.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSRepo extends JpaRepository<Sms, String> {
}
