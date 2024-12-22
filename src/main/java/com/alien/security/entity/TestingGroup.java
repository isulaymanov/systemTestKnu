package com.alien.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "testinggroup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestingGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "student_group_id", nullable = false)
    @JsonBackReference(value = "group-testing-group")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "testing_id", nullable = false)
    @JsonBackReference(value = "testing-testing-group")
    private Testing testing;
}
