package com.sushil.ecommerceproject.inheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_instructor")
public class Instructor extends User {
    private boolean isHandsome;
}
