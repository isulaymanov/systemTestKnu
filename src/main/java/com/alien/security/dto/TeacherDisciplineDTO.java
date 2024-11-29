//package com.alien.security.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//public class TeacherDisciplineDTO {
//    private Long id;
//    private String teacherName;
//    private String disciplineName;
//}


package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherDisciplineDTO {

    private Long id;
    private int teacherId;
    private String teacherName;
    private String teacherLastName;
    private String teacherMiddleName;
    private String teacherUsername;
    private String teacherRole;
    private Long disciplineId;
    private String disciplineName;
    private String disciplineCode;
    private String disciplineDescription;
    private Long departmentId;
    private String departmentName;
    private Long directionId;
    private String directionName;
}
