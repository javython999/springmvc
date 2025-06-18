package com.errday.servlet.reflection;

import com.errday.servlet.reflection.core.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Map;

@SpringBootTest
public class ReflectionTest {

    @Test
    void reflectionTest() throws Exception {
        Class<?> accountClass = Class.forName("com.errday.servlet.reflection.core.Account");
        Constructor<?> declaredConstructor = accountClass.getDeclaredConstructor(Long.class, String.class, String.class);
        Object account = declaredConstructor.newInstance(1L, "springMvc", "test@test.com");

        ReflectionExecutor reflectionExecutor = new ReflectionExecutor(accountClass);

        ReflectionFieldManager fieldManager = reflectionExecutor.getFieldManager();
        fieldManager.setFieldValue(account, "username", "springSecurity");

        ReflectionMethodManager methodManager = reflectionExecutor.getMethodManager();
        Object id = methodManager.invoke(account, "getId");
        Object username = methodManager.invoke(account, "getUsername");
        Object email = methodManager.invoke(account, "getEmail");

        System.out.println("id: " + id);
        System.out.println("username: " + username);
        System.out.println("email: " + email);
    }

    @Test
    void reflectionTest2() throws Exception {
        DomainRepository<Account> accountRepository = new DomainRepository<>(Account.class, "id");

        Map<String, Object> accountData = Map.of("id", 1L, "username", "springMvc", "email", "test@test.com");
        Account account = accountRepository.save(accountData);

        accountRepository.invoke(1L, "setProfile", account.getId() + ": " + account.getUsername() + ": " + account.getEmail());
        System.out.println("profile: " + accountRepository.getExecutor().getFieldManager().getFieldValue(account, "profile"));
    }

    @Test
    void reflectionTest3() throws Exception {
        DomainRepository<Product> productRepository = new DomainRepository<>(Product.class, "id");

        Map<String, Object> productData = Map.of("id", 1L, "name", "productA", "price", 10000);
        Product product = productRepository.save(productData);
        System.out.println("product = " + product);
        System.out.println("discount: " + productRepository.getExecutor().getMethodManager().invoke(product, "discount", 1));
    }
}
