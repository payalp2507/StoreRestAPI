package com.example.springbootrestapi.dto;

import lombok.Data;

@Data
public class StoreDTO {
    private Long storeId;
    private String storeStatus;
    private String userID;
    private String name;
    private String email;
    private String title;
    private String iconPath;
    private AddressDTO storeAddress;
    private String bannerPath;
    private String storyTitle;
    private String announcementTitle;
    private String announcementDescription;
    private String messageToBuyers;
    private boolean orderCustomizationAllowed;
    private String storyDescription;
    private boolean vacationMode;
    private String vacationAutoReplay;
}
