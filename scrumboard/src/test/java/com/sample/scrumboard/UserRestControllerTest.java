package com.sample.scrumboard;

import com.sample.scrumboard.controllers.UserRestController;
import com.sample.scrumboard.models.User;
import com.sample.scrumboard.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRestController userController;

    public UserRestControllerTest() {
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void testFindAll() throws Exception{
        List<User> users = Arrays.asList(
                new User("Nick", "Van Hoof", "Nxtra", "testpass", "nickvanhoof@gmail.com"),
                new User("Lollie", "Lolliita", "lolly", "lolpass", "lol@hotmail.com"));
        when(userRepository.findAll()).thenReturn(users);
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].firstName", is("Nick")))
                .andExpect(jsonPath("$[0].lastName", is("Van Hoof")))
                .andExpect(jsonPath("$[0].userName", is("Nxtra")))
                .andExpect(jsonPath("$[0].passWord", is("testpass")))
                .andExpect(jsonPath("$[0].email", is("nickvanhoof@gmail.com")))
                .andExpect(jsonPath("$[1].firstName", is("Lollie")))
                .andExpect(jsonPath("$[1].lastName", is("Lolliita")))
                .andExpect(jsonPath("$[1].userName", is("lolly")))
                .andExpect(jsonPath("$[1].passWord", is("lolpass")))
                .andExpect(jsonPath("$[1].email", is("lol@hotmail.com")))
                .andExpect(jsonPath("$", hasSize(2)));
        verify(userRepository,times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

}
