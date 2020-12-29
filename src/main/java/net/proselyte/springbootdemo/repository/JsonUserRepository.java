package net.proselyte.springbootdemo.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.proselyte.springbootdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class JsonUserRepository {

    private static final String USER_JSON_FILE = "src/main/resources/user/users.json";
    @Autowired
    private ObjectMapper objectMapper;

    public List<User> findAllUsers() {
        try {
            return Arrays.asList(objectMapper.readValue(new File(USER_JSON_FILE), User[].class));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void writeUsers(List<User> users) {
        try {
            objectMapper.writeValue(new File(USER_JSON_FILE), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}