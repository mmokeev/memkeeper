package ru.memkeeper.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.memkeeper.entities.SimpleEntity;
import ru.memkeeper.repositories.SimpleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SimpleServiceTest {

    @Autowired
    private SimpleService simpleService;

    @Autowired
    private SimpleRepository simpleRepository;

    @Test
    public void testCreateSimpleEntity() {
        String userId = "userId";
        String title = "title";

        Long id = simpleService.createSimple(userId, title);

        SimpleEntity simpleEntity = simpleRepository.findByIdAndUserId(id, userId);
        assertEquals(userId, simpleEntity.getUserId());
        assertEquals(title, simpleEntity.getTitle());
    }
}
