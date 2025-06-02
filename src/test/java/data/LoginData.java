package data;

import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "loginCredential")
    public static Object[][] getloginData(){
        return new Object[][]{
                {"Sinatra@gmail.com", "Sinatra@0212"},
                {"invalid_user@example.com", "Arti12345"},
                {"", "some_password"},
                {"user@example.com", ""},
                {"bad_format", "password123"}
        };
    }
}
