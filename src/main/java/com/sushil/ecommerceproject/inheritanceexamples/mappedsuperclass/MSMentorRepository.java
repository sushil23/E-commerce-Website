package com.sushil.ecommerceproject.inheritanceexamples.mappedsuperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MSMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
}
