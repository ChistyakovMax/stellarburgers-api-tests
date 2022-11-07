package site.stellarburgers.user;

import org.apache.commons.lang3.RandomStringUtils;

public class User {

    private String email;
    private String password;
    private String name;


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User gerRandomUser() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@gmail.com",
                "P@ssw0rd",
                RandomStringUtils.randomAlphanumeric(10)
        );
    }
}