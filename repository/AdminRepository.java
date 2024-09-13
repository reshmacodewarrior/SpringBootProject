package com.universityproject.repository;


import com.universityproject.entity.Admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findAll();
}
