package com.sushil.ecommerceproject;

import com.sushil.ecommerceproject.inheritanceexamples.mappedsuperclass.MSMentorRepository;
import com.sushil.ecommerceproject.inheritanceexamples.mappedsuperclass.Mentor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ECommerceProjectApplicationTests {
	@Autowired
	private MSMentorRepository msMentorRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testDifferentInheritances() {
		Mentor mentor = new Mentor();
		mentor.setEmail("sushil@sclr.com");
		mentor.setPassword("psswrd");
		mentor.setNumberOfMentees(4);
		mentor.setNumberOfSessions(50);
		msMentorRepository.save(mentor);
	}
}
