package ru.memkeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.memkeeper.entities.Tab;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabsRepository extends JpaRepository<Tab, Long> {

    @Transactional
    Long countByUserId(String userId);

    @Transactional
    List<Tab> findByUserId(String userId);

    @Transactional
    Optional<Tab> findByIdAndUserId(Long Id, String userId);

    @Transactional
    @Modifying
    @Query("update Tab t set t.isActive = false where t.userId = :userId")
    void markUserTabsAsInactive(@Param("userId") String userId);
}
