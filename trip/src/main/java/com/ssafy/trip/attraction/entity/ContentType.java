package com.ssafy.trip.attraction.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "contenttypes")
public class ContentType {

    @Id
    @Column(name = "content_type_id")
    private Integer contentTypeId;

    @Column(name = "content_type_name", length = 45)
    private String contentTypeName;

    // Constructors, getters, and setters

    public ContentType() {}

    // Getters and setters (omitted for brevity, but should be included)

    @Override
    public String toString() {
        return "ContentType{" +
                "contentTypeId=" + contentTypeId +
                ", contentTypeName='" + contentTypeName + '\'' +
                '}';
    }
}