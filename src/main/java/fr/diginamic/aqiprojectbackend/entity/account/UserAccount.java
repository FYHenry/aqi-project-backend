package fr.diginamic.aqiprojectbackend.entity.account;

import fr.diginamic.aqiprojectbackend.entity.map.Bookmark;

import java.util.List;

public class UserAccount {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserStatus userStatus;
    private Address address;
    private List<Bookmark> bookmarks;
    private Credential credential;
}
