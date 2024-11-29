package com.alien.security.entity;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "teacherDiscipline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TeacherDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    //    @JsonIgnore
    @JsonBackReference
    private UserModel user;

    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    //@JsonIgnore
    @JsonBackReference
    private Discipline discipline;
}
