package com.vbhoomidi.EmailService;

import com.vbhoomidi.entity.Email;

/**
 * Created by vikramreddy on 7/13/2017.
 */
public interface EmailService {
    void receiveMessage(Email email);
    void SendEmail(Email email);
}
