package com.guild.demo.repository;
import com.guild.demo.entity.Mage;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MageRepository extends JpaRepository<Mage, Integer> {}