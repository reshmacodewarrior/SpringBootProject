package com.universityproject.repository;

import com.universityproject.entity.Faculty;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByEmail(String email); // Email-based lookup
    Faculty findByName(String name);

}

   