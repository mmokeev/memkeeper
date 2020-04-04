package ru.memkeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.memkeeper.entities.SimpleEntity;
import ru.memkeeper.json.SimpleJson;
import ru.memkeeper.repositories.SimpleRepository;

import java.util.List;

@Service
public class SimpleService {

    private final SimpleRepository simpleRepository;

    @Autowired
    public SimpleService(SimpleRepository simpleRepository) {
        this.simpleRepository = simpleRepository;
    }

    public Long createSimple(String userId, String title) {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setUserId(userId);
        simpleEntity.setTitle(title);
        return simpleRepository.save(simpleEntity).getId();
    }

    public SimpleJson findSimple(String userId, Long id) {
        SimpleEntity simpleEntity = simpleRepository.findByIdAndUserId(id, userId);

        return new SimpleJson.Builder()
                .id(simpleEntity.getId())
                .userId(simpleEntity.getUserId())
                .title(simpleEntity.getTitle())
                .build();
    }

    public List<Long> findAllId(String userId) {
        return simpleRepository.findIdsByUserId(userId);
    }
}
