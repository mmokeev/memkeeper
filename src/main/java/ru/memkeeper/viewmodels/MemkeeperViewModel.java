package ru.memkeeper.viewmodels;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import ru.memkeeper.controllers.MainPageServiceController;
import ru.memkeeper.data.AddNoteData;
import ru.memkeeper.data.NoteData;
import ru.memkeeper.data.TabData;
import ru.memkeeper.domain.Note;
import ru.memkeeper.domain.Tab;
import ru.memkeeper.services.MainPageService;
import ru.memkeeper.services.NoteService;
import ru.memkeeper.services.TabService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MemkeeperViewModel {

    private static final String TEMP_USER_ID = "temp user id";

    @WireVariable
    private TabService tabService;
    @WireVariable
    private NoteService noteService;
    @WireVariable
    private MainPageService mainPageService;

    private MainPageServiceController mainController;

    private final List<Tab> tabs = new ArrayList<>();

    @Init
    public void init() {
        mainController = new MainPageServiceController(tabService, noteService, mainPageService);

        for (TabData tabData : mainController.getTabs(TEMP_USER_ID)) {
            List<Note> notes = mainController.getNotes(TEMP_USER_ID, tabData.id()).stream()
                    .map(noteData -> new Note(noteData.id(), noteData.title(), noteData.createdAt(), noteData.text()))
                    .collect(Collectors.toList());
            notes.add(new Note(0, tabData.name() + " heh", new Date(), Optional.of("my super text hehe")));
            tabs.add(new Tab(tabData.id(), tabData.name(), tabData.isActive(), notes));
        }
    }

    @Command
    @NotifyChange("tabs")
    public void markTabAsActive(@BindingParam("tabIndex") int tabIndex) {
        tabs.forEach(tab -> tab.setActive(false));
        tabs.get(tabIndex).setActive(true);
    }

    @Command
    public void addNote(@ContextParam(ContextType.VIEW) Component container,
                        @BindingParam("tabId") long tabId,
                        @BindingParam("tabName") String tabName) {
        Map<String, Object> args = Map.of("tabId", tabId, "tabName", tabName);
        Component editor = Executions.createComponents("~./zul/add-note.zul", container, args);
        editor.addEventListener("onAddNote", event -> {
            AddNoteData addNoteData = (AddNoteData) event.getData();
            editor.detach();
            NoteData newNoteData = mainController.addNote(TEMP_USER_ID, addNoteData);
            Tab currentTab = tabs.stream()
                    .filter(tab -> tab.getId() == tabId)
                    .findFirst()
                    .orElseThrow();
            currentTab.getNotes().add(
                    new Note(newNoteData.id(), newNoteData.title(), newNoteData.createdAt(), newNoteData.text()));
            BindUtils.postNotifyChange(null, null, this, "tabs");
        });
    }

    public List<Tab> getTabs() {
        return tabs;
    }

}
