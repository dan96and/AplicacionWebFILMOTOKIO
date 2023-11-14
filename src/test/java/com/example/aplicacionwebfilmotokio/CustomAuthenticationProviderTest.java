package com.example.aplicacionwebfilmotokio;

import com.example.aplicacionwebfilmotokio.auth.CustomAuthenticationProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomAuthenticationProviderTest {
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Test
    public void testGetSubStringFromLongToken() {
        String token = "{\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\"}";
        String result = authenticationProvider.getSubstringFromToken(token);
        String expect = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        assertEquals(expect, result);
    }

    @Test
    public void testGetSubStringFromShortToken() {
        String token = "{\"token\":\"eyJhbGciOiJIUzI1NiIsI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG90IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwJV_adQssw5c\"}";
        String result = authenticationProvider.getSubstringFromToken(token);
        String expect = "eyJhbGciOiJIUzI1NiIsI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG90IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwJV_adQssw5c";
        assertEquals(expect, result);
    }
    @Test
    public void testGetSubStringFromEmptyToken(){
        String token = "";
        String result = authenticationProvider.getSubstringFromToken(token);
        String expect = "";
        assertEquals(expect, result);
    }
}