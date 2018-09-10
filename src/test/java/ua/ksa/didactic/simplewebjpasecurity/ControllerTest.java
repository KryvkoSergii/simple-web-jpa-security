package ua.ksa.didactic.simplewebjpasecurity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.ksa.didactic.simplewebjpasecurity.core.model.User;
import ua.ksa.didactic.simplewebjpasecurity.core.web.Controller;
import ua.ksa.didactic.simplewebjpasecurity.core.web.UserController;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void echo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(Controller.ECHO).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"message\":\"ok\"}")));
    }

    @Test
    public void echoDB() throws Exception {
        User user = new User("user-name");
        mvc.perform(MockMvcRequestBuilders.post(UserController.URL, userJson(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", user.getName()));
    }

    private String userJson(User name) throws JsonProcessingException {
        String json = mapper.writeValueAsString(name);
        logger.info(json);
        return json;
    }

}