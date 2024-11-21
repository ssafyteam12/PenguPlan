package com.ssafy.trip.attraction.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "guguns",
        indexes = {
                @Index(name = "gugun_code_idx", columnList = "gugun_code"),
                @Index(name = "guguns_sido_to_sidos_cdoe_fk_idx", columnList = "sido_code")
        })
public final class Gugun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "sido_code")
    private Integer sidoCode;

    @Column(name = "gugun_code")
    private Integer gugunCode;

    @Column(name = "gugun_name", length = 20)
    private String gugunName;


    // Constructors, getters, and setters

    public Gugun() {}

    // Getters and setters (omitted for brevity, but should be included)

    @Override
    public String toString() {
        return "Gugun{" +
                "no=" + no +
                ", sidoCode=" + sidoCode +
                ", gugunCode=" + gugunCode +
                ", gugunName='" + gugunName + '\'' +
                '}';
    }
}