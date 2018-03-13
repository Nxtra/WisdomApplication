package com.sample.scrumboard;

import static org.assertj.core.api.Assertions.assertThat;

import com.sample.scrumboard.Controllers.HomeController;
import com.sample.scrumboard.Controllers.UserController;
import com.sample.scrumboard.Controllers.UserRestController;
import com.sample.scrumboard.Controllers.UserStoryRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private UserController userController;


    @Autowired
    private UserRestController userRestController;

    @Autowired
    private UserStoryRestController userStoryRestController;

    public SmokeTest(){
    }

    @Test
    public void controllerTest() throws Exception {
        //true, check not null
        assertNotNull(homeController);
        assertNotNull(userController);
        assertNotNull(userRestController);
        assertNotNull(userStoryRestController);
    }
}
