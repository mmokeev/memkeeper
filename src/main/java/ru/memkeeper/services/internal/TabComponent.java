package ru.memkeeper.services.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.memkeeper.entities.Tab;
import ru.memkeeper.repositories.TabsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class TabComponent {

    private final TabsRepository tabsRepository;

    @Autowired
    public TabComponent(TabsRepository tabsRepository) {
        this.tabsRepository = tabsRepository;
    }

    @Transactional
    public void markAllUserTabsAsInactive(String userId) {
        tabsRepository.markUserTabsAsInactive(userId);
    }

    @Transactional
    public List<Tab> getUserTabs(String userId) {
        return tabsRepository.findByUserId(userId);
    }

    @Transactional
    public Tab findTab(String userId, Long tabId) {
        return tabsRepository.findByIdAndUserId(tabId, userId).orElseThrow(() ->
                new IllegalStateException(String.format("can not find tab by user: %s and id: %s", userId, tabId)));
    }

    @Transactional
    public void deleteTab(String userId, Long tabId) {
        tabsRepository.deleteByUserIdAndNoteId(userId, tabId);
    }

    @Transactional
    public Tab updateTab(Tab tab) {
        return tabsRepository.saveAndFlush(tab);
    }

    @Transactional
    public Tab createNewTab(String userId, String name, boolean isActive) {
        Tab newTab = new Tab();
        newTab.setUserId(userId);
        newTab.setName(name);
        newTab.setActive(isActive);

        return tabsRepository.save(newTab);
    }

    @Transactional
    public Long getUserTabsCount(String userId) {
        return tabsRepository.countByUserId(userId);
    }
}
