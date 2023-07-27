package com.icritic.notifications.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordResetRequest {

    private Long userId;
    private String email;
    private String passwordResetHash;
}
