package ru.memkeeper.viewmodels;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import ru.memkeeper.controllers.MainPageServiceController;
import ru.memkeeper.data.AddNoteData;
import ru.memkeeper.data.NoteData;
import ru.memkeeper.data.TabData;
import ru.memkeeper.services.MainPageService;
import ru.memkeeper.services.NoteService;
import ru.memkeeper.services.TabService;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MemkeeperViewModel {

    private static final String TEMP_USER_ID = "temp user id";
    private static final String DELETE_TAB_WARNING_MESSAGE_PATTERN =
            "Вы уверены, что хотите удалить вкладку \"%s\"? " +
            "Вместе с вкладкой будут удалены все ее заметки.";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @WireVariable
    private TabService tabService;
    @WireVariable
    private NoteService noteService;
    @WireVariable
    private MainPageService mainPageService;

    private MainPageServiceController mainController;

    private List<TabData> tabs;
    private List<NoteData> notes;

    private String newTabName;

    @Init
    public void init() {
        mainController = new MainPageServiceController(tabService, noteService, mainPageService);

        newTabName = "";
        tabs = mainController.getTabs(TEMP_USER_ID);
        IntStream.range(0, tabs.size())
                .filter(tabIndex -> tabs.get(tabIndex).isActive())
                .findFirst()
                .ifPresentOrElse(this::selectTab, () -> notes = Collections.emptyList());
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    @Command
    @NotifyChange({"tabs", "notes"})
    public void selectTab(@BindingParam("tabIndex") int tabIndex) {
        loadNotesByTabId(tabs.get(tabIndex).id());
        tabs = mainController.getTabs(TEMP_USER_ID);
        //TODO: как-то обработать ситуацию, когда вкладка почему-то изменилась
    }

    private void loadNotesByTabId(long tabId) {
        notes = mainController.getNotes(TEMP_USER_ID, tabId);
    }

    @Command
    public void deleteTab(@BindingParam("tabIndex") int tabIndex) {
        TabData tab = tabs.get(tabIndex);
        Messagebox.show(String.format(DELETE_TAB_WARNING_MESSAGE_PATTERN, tab.name()), "Внимание!",
                new Messagebox.Button[]{Messagebox.Button.OK, Messagebox.Button.NO},
                new String[] {"Да", "Нет"}, Messagebox.EXCLAMATION, null,
                event -> {
                    if (Messagebox.ON_OK.equals(event.getName())) {
                        mainController.deleteTab(TEMP_USER_ID, tab.id());
                        tabs = mainController.getTabs(TEMP_USER_ID);
                        if (!tabs.isEmpty()) {
                            int newTabIndex = tabIndex < tabs.size() ? tabIndex : tabIndex - 1;
                            loadNotesByTabId(tabs.get(newTabIndex).id());
                            tabs = mainController.getTabs(TEMP_USER_ID);
                            BindUtils.postNotifyChange(null, null, this, "tabs", "notes");
                        }
                    }
                });
    }

    @Command
    @NotifyChange({"tabs", "notes"})
    public void addTab() {
        TabData newTab = mainController.addTab(TEMP_USER_ID, newTabName);
        newTabName = "";
        tabs = mainController.getTabs(TEMP_USER_ID);
        notes = Collections.emptyList();
        //TODO: обрабатывать ситуацию, когда добавить вкладку не удалось (для этого понадобится переменная newTab)
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
            mainController.addNote(TEMP_USER_ID, addNoteData);
            loadNotesByTabId(tabId);
            BindUtils.postNotifyChange(null, null, this, "notes");
        });
    }

    @Command
    @NotifyChange("notes")
    public void deleteNote(@BindingParam("tabId") long tabId,
                           @BindingParam("noteId") long noteId) {
        mainController.deleteNote(TEMP_USER_ID, noteId);
        loadNotesByTabId(tabId);
    }

    public List<TabData> getTabs() {
        return tabs;
    }

    public List<NoteData> getNotes() {
        return notes;
    }

    public String getNewTabName() {
        return newTabName;
    }

    public void setNewTabName(String newTabName) {
        this.newTabName = newTabName;
    }
}
