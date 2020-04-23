package ru.memkeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.memkeeper.entities.Tab;
import ru.memkeeper.services.internal.TabComponent;

import java.util.List;

@Service
public class TabService {

    private final TabComponent tabComponent;

    @Autowired
    public TabService(TabComponent tabComponent) {
        this.tabComponent = tabComponent;
    }

    public Tab findTabAndMarkItAsActive(String userId, Long tabId) {
        tabComponent.markAllUserTabsAsInactive(userId);
        Tab tab = tabComponent.findTab(userId, tabId);
        tab.setActive(true);
        return tabComponent.updateTab(tab);
    }

    public List<Tab> getUserTabs(String userId) {
        if (tabComponent.getUserTabsCount(userId) == 0) {
            createDefaultTabs(userId);
        }

        return tabComponent.getUserTabs(userId);
    }

    public void deleteTab(String userId, Long tabId) {
        tabComponent.deleteTab(userId, tabId);
    }

    public Tab createNewTabAndMarkAsActive(String userId, String name) {
        tabComponent.markAllUserTabsAsInactive(userId);
        return tabComponent.createNewTab(userId, name, true);
    }

    private void createDefaultTabs(String userId) {
        tabComponent.createNewTab(userId, "Фильмы", true);
        tabComponent.createNewTab(userId, "Книги", false);
        tabComponent.createNewTab(userId, "Видосики", false);
    }
}
