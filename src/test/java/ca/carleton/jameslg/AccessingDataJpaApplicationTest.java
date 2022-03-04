package ca.carleton.jameslg;

import static org.assertj.core.api.Assertions.assertThat;

import ca.carleton.jameslg.controllers.AddressBookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccessingDataJpaApplicationTest {

    @Autowired
    private AddressBookController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
