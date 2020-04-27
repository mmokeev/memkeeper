package ru.memkeeper.viewmodels;

import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import ru.memkeeper.data.AddNoteData;

import java.util.Date;

public class AddNoteViewModel {

    private Long tabId;
    private String tabName;

    private String title;
    private Date createdAt;
    private String text;

    @Init
    public void init(@ExecutionArgParam("tabId") Long tabId,
                     @ExecutionArgParam("tabName") String tabName) {
        this.tabId = tabId;
        this.tabName = tabName;

        title = "";
        createdAt = new Date();
        text = "";
    }

    @Command
    public void cancel(@ContextParam(ContextType.VIEW) Component container) {
        container.detach();
    }

    @Command
    public void addNote(@ContextParam(ContextType.VIEW) Component container) {
        Events.sendEvent("onAddNote", container, getAddNoteData());
    }

    private AddNoteData getAddNoteData() {
        return new AddNoteData.Builder()
                .tabId(tabId)
                .title(title)
                .createdAt(createdAt)
                .text(text)
                .build();
    }

    public String getTabName() {
        return tabName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
