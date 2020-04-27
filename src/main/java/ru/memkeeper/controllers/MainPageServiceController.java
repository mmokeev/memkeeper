package ru.memkeeper.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.memkeeper.data.AddNoteData;
import ru.memkeeper.data.NoteData;
import ru.memkeeper.data.TabData;
import ru.memkeeper.helpers.Converter;
import ru.memkeeper.helpers.Examples;
import ru.memkeeper.services.MainPageService;
import ru.memkeeper.services.NoteService;
import ru.memkeeper.services.TabService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/mainKeeper", produces = APPLICATION_JSON_VALUE)
@Api(tags = "[Основной контроллер] Ну тут вся логика :)")
public class MainPageServiceController {

    private final TabService tabService;
    private final NoteService noteService;
    private final MainPageService mainPageService;

    @Autowired
    public MainPageServiceController(TabService tabService, NoteService noteService, MainPageService mainPageService) {
        this.tabService = tabService;
        this.noteService = noteService;
        this.mainPageService = mainPageService;
    }

    @GetMapping("/{userId}/getTabs")
    @ApiOperation("Получить вкладки для юзера")
    public List<TabData> getTabs(
            @ApiParam(value = "Идентификатор юзера", required = true, example = Examples.USER_ID)
            @PathVariable String userId) {

        return tabService.getUserTabs(userId).stream()
                .map(Converter::convertTabToTabData)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/addTab")
    @ApiOperation("Добавить вкладку")
    public TabData addTab(
            @ApiParam(value = "Идентификатор юзера", required = true, example = Examples.USER_ID)
            @PathVariable String userId,
            @ApiParam(value = "Название вкладки", required = true, example = Examples.LONG_ID)
            @RequestParam String tabName) {

        return Converter.convertTabToTabData(tabService.createNewTabAndMarkAsActive(userId, tabName));
    }

    @DeleteMapping("/{userId}/deleteTab")
    @ApiOperation("Удалить вкладку")
    public void deleteTab(
            @ApiParam(value = "Идентификатор юзера", required = true, example = Examples.USER_ID)
            @PathVariable String userId,
            @ApiParam(value = "Идентификатор вкладки", required = true, example = Examples.LONG_ID)
            @RequestParam Long tabId) {

        tabService.deleteTab(userId, tabId);
    }

    @GetMapping("/{userId}/getNotes")
    @ApiOperation("Получить заметки юзера по id пользователя и id вкладки")
    public List<NoteData> getNotes(
            @ApiParam(value = "Идентификатор юзера", required = true, example = Examples.USER_ID)
            @PathVariable String userId,
            @ApiParam(value = "Идентификатор вкладки", required = true, example = Examples.LONG_ID)
            @RequestParam Long tabId) {

        return mainPageService.findNotes(userId, tabId).stream()
                .map(Converter::convertNoteToData)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/addNote")
    @ApiOperation("Добавить заметку во вкладку")
    public NoteData addNote(
            @ApiParam(value = "Идентификатор юзера", required = true, example = Examples.USER_ID)
            @PathVariable String userId,
            @ApiParam(value = "Заметка на добавление", required = true)
            @RequestBody AddNoteData addNoteData) {

        return Converter.convertNoteToData(noteService.addNewNote(
                userId,
                addNoteData.tabId(),
                addNoteData.title(),
                addNoteData.text().orElse(null),
                addNoteData.createdAt().orElse(null)
        ));
    }

    @DeleteMapping("/{userId}/deleteNote")
    @ApiOperation("Удалить заметку")
    public void deleteNote(
            @ApiParam(value = "Идентификатор юзера", required = true, example = Examples.USER_ID)
            @PathVariable String userId,
            @ApiParam(value = "Идентификатор заметки", required = true, example = Examples.LONG_ID)
            @RequestParam Long noteId) {

        noteService.deleteNote(userId, noteId);
    }

}
