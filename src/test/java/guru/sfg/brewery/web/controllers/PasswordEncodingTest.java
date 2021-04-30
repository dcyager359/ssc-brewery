package guru.sfg.brewery.web.controllers;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.util.DigestUtils;
import org.thymeleaf.cache.StandardParsedTemplateEntryValidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingTest {

    public static final String PASSWORD = "password";


    @Test
    void testDelegatingEncoder() {

        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        System.out.println(bcrypt.encode("hello"));
        System.out.println(bcrypt.encode("hello"));
    }

    @Test
    void testSha256() {
        PasswordEncoder sha = new StandardPasswordEncoder();
        System.out.println(sha.encode(PASSWORD));
        System.out.println(sha.encode(PASSWORD));

    }

    @Test
    void testLdap() {
        PasswordEncoder ldap = new LdapShaPasswordEncoder();
        System.out.println(ldap.encode("tiger"));
        System.out.println(ldap.encode("tiger"));

        String encodedPassword = ldap.encode("tiger");
        assertTrue(ldap.matches("tiger", encodedPassword));
    }

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
