package com.application.management.order.client.functionaltests;


import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Wait
public class ClientsPageFT extends FluentTest {

    /*
     * Data is loaded from DataLoader
     * FluentLenium framework is used
     *
     * HtmlUnit does not play nicely with jQuery
     * */

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws  Exception {
        goTo("http://localhost:" + port + "/pages/clients/clients.html");
    }


    @Test
    public void goToClientsPage() throws Exception {
        assertThat(window().title()).isEqualTo("Clients");
    }

    @Test
    public void goToAddClientPage() throws Exception{
        find("#client_add").click();
        assertThat(window().title()).isEqualTo("Clients Add");
    }


}
