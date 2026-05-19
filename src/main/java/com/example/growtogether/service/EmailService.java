package com.example.growtogether.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface EmailService {

    String sendInvitation(String email);

}
