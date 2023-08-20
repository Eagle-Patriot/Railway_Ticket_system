package com.mainproject.demo.Tripmate.Repository;

import com.mainproject.demo.Tripmate.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//this interface is generally for the database queries
@Repository
@EnableJpaRepositories
public interface TripmateRepository
        extends JpaRepository<Users, Integer> {

 boolean existsById(int userId);
 Optional<Users> findByEmail(String email);
 Optional<Users> findById(int userId);

  @Transactional
  @Modifying
  @Query("UPDATE Users a " +
          "SET a.enabled = TRUE WHERE a.email = ?1")
  int enableUser(String email);
}
