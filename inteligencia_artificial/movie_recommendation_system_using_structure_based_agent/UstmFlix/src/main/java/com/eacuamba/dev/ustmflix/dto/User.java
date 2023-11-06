package com.eacuamba.dev.ustmflix.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    private Long id;
    private String name;
    private Preferences preferences;
}
