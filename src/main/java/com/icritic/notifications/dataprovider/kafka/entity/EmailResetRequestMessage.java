package com.icritic.notifications.dataprovider.kafka.entity;

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
public class EmailResetRequestMessage {

    private Long userId;
    private String email;
    private String emailResetHash;
}
