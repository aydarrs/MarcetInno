package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeID;

    @Column(name = "access_id")
    private Long accessID;

    @ManyToOne
    private Shop shop;

    @Column(name = "employee_first_name")
    private String employeeFirstName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    private String email;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_number")
    private int jobNumber;

    private String login;
    private String password;

}
