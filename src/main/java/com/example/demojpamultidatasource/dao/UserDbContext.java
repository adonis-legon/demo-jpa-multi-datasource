package com.example.demojpamultidatasource.dao;

public class UserDbContext {
    private static final ThreadLocal<UserMembershipType> contextHolder = new ThreadLocal<>();

    public static void setCurrentDb(UserMembershipType dbType) {
        contextHolder.set(dbType);
    }

    public static UserMembershipType getCurrentDb() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
