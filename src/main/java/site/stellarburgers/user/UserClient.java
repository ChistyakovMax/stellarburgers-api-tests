package site.stellarburgers.user;

import io.qameta.allure.Step;
import site.stellarburgers.config.BaseClient;
import org.junit.Assert;

public class UserClient extends BaseClient {

    private final String REGISTER = "/auth/register";

    @Step("Создание пользователя")
    public boolean create(User user) {
        return getSpec()
                .body(user)
                .when()
                .post(REGISTER)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("success");
    }

    @Step("Cоздать пользователя, который уже зарегистрирован")
    public String createInvalid(User user){
        return getSpec()
                .body(user)
                .when()
                .post(REGISTER)
                .then().log().all()
                .assertThat()
                .statusCode(403)
                .extract()
                .path("message");
    }

    @Step("Cоздать пользователя и не заполнить одно из обязательных полей")
    public void createWithNullField(User user, String field){
        user = initializeInvalid(user, field);
        String msg = createInvalid(user);
        Assert.assertEquals("Email, password and name are required fields", msg);
    }

    @Step("Инициализировать невалидного пользователя")
    public User initializeInvalid(User user, String field) {
        switch (field) {
            case ("email"):
                user.setEmail(null);
                return user;

            case ("password"):
                user.setPassword(null);
                return user;
            case ("name"):
                user.setName(null);
                return user;
            default:
                return user;
        }
    }

}
