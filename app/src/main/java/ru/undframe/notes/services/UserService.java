package ru.undframe.notes.services;


import org.jetbrains.annotations.NotNull;

import ru.undframe.notes.data.User;


public class UserService {

    private final User currentUser = User.getInstance();

    public User getCurrentUser(){
        return currentUser;
    }

    public void updateUser(@NotNull User user) {
        currentUser.fillData(user);
    }
}
