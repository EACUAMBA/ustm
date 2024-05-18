package com.eacuamba.dev.implementing_aspects.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    private String number;
    private Double balance;
    private String clientName;
    private String password;
}
