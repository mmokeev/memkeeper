package ru.memkeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.memkeeper.entities.SimpleEntity;

import java.util.List;

@Repository
public interface SimpleRepository extends JpaRepository<SimpleEntity, Long> {

    SimpleEntity findByIdAndUserId(Long id, String userId);

    @Query("select s from SimpleEntity s where s.userId = :userId")
    List<Long> findIdsByUserId(@Param("userId") String userId);
}
