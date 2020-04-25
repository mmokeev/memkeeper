package ru.memkeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.memkeeper.entities.Note;
import ru.memkeeper.entities.Tab;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserIdAndTab(String userId, Tab tab);

    @Modifying
    @Query("delete from Note n where n.id = :noteId and n.userId = :userId")
    void deleteByUserIdAndNoteId(@Param("userId") String userId, @Param("noteId") Long noteId);
}
