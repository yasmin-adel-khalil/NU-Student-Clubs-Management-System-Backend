package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByName(String name);

    Optional<Club> findByEmail(String email);

    List<Club> findByCategory(String category);

    @Query("SELECT c FROM Club c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Club> searchByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT c FROM Club c WHERE LOWER(c.category) = LOWER(:category)")
    Page<Club> findByCategoryPaginated(@Param("category") String category, Pageable pageable);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
