package ru.memkeeper.viewmodels;

import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import ru.memkeeper.data.AddNoteData;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;

public class AddNoteViewModel {

    private Long tabId;
    private String tabName;

    private String title;
    private Date createdAt;
    private Optional<String> text;

    @Init
    public void init(@ExecutionArgParam("tabId") Long tabId,
                     @ExecutionArgParam("tabName") String tabName) {
        this.tabId = tabId;
        this.tabName = tabName;

        title = "";
        createdAt = new Date();
        text = Optional.empty();
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
        return text.orElse("");
    }

    public void setText(String text) {
        this.text = isNullOrEmpty(text) ? Optional.empty() : Optional.of(text);
    }
}
