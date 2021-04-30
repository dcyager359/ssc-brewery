package guru.sfg.brewery.web.controllers;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class PasswordEncodingTest {

    public static final String PASSWORD = "password";

    @Test
    void testNooP() {
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
        System.out.println(noOp.encode(PASSWORD));
    }



    @Test
    public void hashingExample() {
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        String salted = PASSWORD + "Thisisasaltvalue";
        System.out.println(DigestUtils.md5DigestAsHex(salted.getBytes()));
    }
}
