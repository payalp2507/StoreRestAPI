package com.example.springbootrestapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(schema = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_status", length = 15, nullable = false)
    private String storeStatus;

    @Column(name = "user_id", length = 40, nullable = false)
    private String userID;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "icon_path", nullable = false)
    private String iconPath;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "store_address")
    private Address address;

    @Column(name = "banner_path")
    private String bannerPath;

    @Column(name = "story_title", length = 80, nullable = false)
    private String storyTitle;

    @Column(name = "announcement_title", length = 50)
    private String announcementTitle;

    @Column(name = "announcement_description")
    private String announcementDescription;

    @Column(name = "message_to_buyers", length = 100, nullable = false)
    private String messageToBuyers;

    @Column(name = "order_customization_allowed", nullable = false)
    private boolean orderCustomizationAllowed;

    @Column(name = "story_description",  nullable = false)
    private String storyDescription;

    @Column(name = "vacation_mode", nullable = false)
    private boolean vacationMode;

    @Column(name = "vacation_auto_replay", nullable = false)
    private String vacationAutoReplay;

}
