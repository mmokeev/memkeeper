package ru.memkeeper.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.memkeeper.entities.SimpleEntity;
import ru.memkeeper.json.SimpleJson;
import ru.memkeeper.services.SimpleService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/simple", produces = APPLICATION_JSON_VALUE)
@Api(tags = "[Проверка] Самый простой контроллер")
public class SimpleController {

    private final SimpleService simpleService;

    @Autowired
    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @PostMapping("/add/{userId}")
    @ApiOperation("Создать простую ентитю")
    public Long createEntity(
            @ApiParam(value = "Идентификатор юзера", required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "Title") @RequestParam(value = "title", required = false) String title) {
        return simpleService.createSimple(userId, title);
    }

    @GetMapping("/{userId}/{id}")
    @ApiOperation("Получить простую ентитю по id")
    public SimpleJson getJson(
            @ApiParam(value = "Идентификатор юзера", required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "Id", required = true) @PathVariable(value = "id") Long id) {
        return simpleService.findSimple(userId, id);
    }

    @GetMapping("/{userId}")
    @ApiOperation("Получить список id")
    public List<Long> findAllIdsByUserId(
            @ApiParam(value = "Идентификатор юзера", required = true) @PathVariable("userId") String userId) {
        return simpleService.findAllId(userId);
    }

}
