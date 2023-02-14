package com.kouiz.demo.repository;

import com.kouiz.demo.entity.Tests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends JpaRepository<Tests,Integer> {

    public Tests findById(int id);

}
