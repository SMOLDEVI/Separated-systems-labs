package com.guild.demo.repository;
import com.guild.demo.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArtifactRepository extends JpaRepository<Artifact, Integer> {}