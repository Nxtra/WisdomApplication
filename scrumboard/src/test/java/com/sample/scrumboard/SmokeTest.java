package com.sample.scrumboard;

import com.sample.scrumboard.controllers.HomeController;
import com.sample.scrumboard.controllers.UserController;
import com.sample.scrumboard.controllers.UserRestController;
import com.sample.scrumboard.controllers.UserStoryRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(homeController).isNotNull();
        assertThat(userController).isNotNull();
        assertNotNull(userRestController);
        assertNotNull(userStoryRestController);
    }
}
