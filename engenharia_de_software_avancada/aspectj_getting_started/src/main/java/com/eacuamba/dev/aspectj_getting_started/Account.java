package com.eacuamba.dev.aspectj_getting_started;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String number;
    private Double balance;
    private String clientName;
    private String password;
}