package data;

import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "loginCredential")
    public static Object[][] getloginData(){
        return new Object[][]{
                {"Sinatra@gmail.com", "Sinatra@0212", true},
                {"invalid_user@example.com", "Arti12345", false},
                {"", "some_password", false},
                {"user@example.com", "", false},
                {"bad_format", "password123", false}
        };
    }
}
