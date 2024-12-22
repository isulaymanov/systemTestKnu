package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestingGroupDTO {
    private Long id;
    private GroupDTO group;
    private TestingDTO testing;
}
