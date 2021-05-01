package guru.sfg.brewery.web.controllers;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.util.DigestUtils;
import org.springframework.util.SocketUtils;
import org.thymeleaf.cache.StandardParsedTemplateEntryValidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingTest {

    public static final String PASSWORD = "password";


    @Test
    void testDelegatingEncoder() {

//        PasswordEncoder bcrypt = new BCryptPasswordEncoder(15); //3695ms
        PasswordEncoder bcrypt = new BCryptPasswordEncoder(18); //22488ms

        long start = System.nanoTime();
        String encoded = bcrypt.encode("tiger");
        long end = System.nanoTime();

        System.out.println("time to generate for strength of 15: " + (end-start)/1000000);

        System.out.println(encoded);


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
