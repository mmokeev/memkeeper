package ru.memkeeper.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@SequenceGenerator(name = "user_info_pk_seq", sequenceName = "user_info_pk_seq")
public class UserInfo {

    // TODO сделать генерацию userId и сделать это айдишником (видимо, вместе с авторизацией и разными юзерами)
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "notes_pk_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "create_default_tabs", nullable = false)
    private Boolean createDefaultTabs = false;

    public Boolean getCreateDefaultTabs() {
        return createDefaultTabs;
    }

    public void setCreateDefaultTabs(Boolean createDefaultTabs) {
        this.createDefaultTabs = createDefaultTabs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
