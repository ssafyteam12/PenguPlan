package com.ssafy.trip.attraction.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sidos")
public class Sido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "sido_code", unique = true)
    private Integer sidoCode;

    @Column(name = "sido_name", length = 20)
    private String sidoName;

    // Constructors, getters, and setters

    public Sido() {}

    // Getters and setters (omitted for brevity, but should be included)

    @Override
    public String toString() {
        return "Sido{" +
                "no=" + no +
                ", sidoCode=" + sidoCode +
                ", sidoName='" + sidoName + '\'' +
                '}';
    }
}