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
import ru.memkeeper.services.MainPageService;
import ru.memkeeper.services.NoteService;
import ru.memkeeper.services.TabService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MemkeeperViewModel {

    private static final String TEMP_USER_ID = "temp user id";

    @WireVariable
    private TabService tabService;
    @WireVariable
    private NoteService noteService;
    @WireVariable
    private MainPageService mainPageService;

    private MainPageServiceController mainController;

    private List<TabData> tabs;
    private List<NoteData> notes;

    @Init
    public void init() {
        mainController = new MainPageServiceController(tabService, noteService, mainPageService);

        tabs = mainController.getTabs(TEMP_USER_ID);
        IntStream.range(0, tabs.size())
                .filter(tabIndex -> tabs.get(tabIndex).isActive())
                .findFirst()
                .ifPresent(this::selectTab);
    }

    @Command
    @NotifyChange({"tabs", "notes"})
    public void selectTab(@BindingParam("tabIndex") int tabIndex) {
        notes = mainController.getNotes(TEMP_USER_ID, tabs.get(tabIndex).id());
        tabs = mainController.getTabs(TEMP_USER_ID);
        //TODO: как-то обработать ситуацию, когда вкладка почему-то изменилась
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
            notes.add(newNoteData);
            BindUtils.postNotifyChange(null, null, this, "notes");
        });
    }

    public List<TabData> getTabs() {
        return tabs;
    }

    public List<NoteData> getNotes() {
        return notes;
    }

}
