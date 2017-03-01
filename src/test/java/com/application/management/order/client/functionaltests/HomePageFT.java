package com.application.management.order.client.functionaltests;


import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageFT extends FluentTest {

    @LocalServerPort
    private int port;


    @Test
    public void gotToHomePage() throws Exception {
        goTo("http://localhost:" + port);
        assertThat(window().title()).isEqualTo("Welcome page");
        assertThat($("p").text()).isEqualTo("Welcome page. Client Order Management Application");

    }


}
