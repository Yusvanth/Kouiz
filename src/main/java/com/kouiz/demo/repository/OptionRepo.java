package com.kouiz.demo.repository;

import com.kouiz.demo.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepo extends JpaRepository<Option,Integer> {
}

