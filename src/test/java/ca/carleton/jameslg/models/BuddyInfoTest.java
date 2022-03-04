package ca.carleton.jameslg.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuddyInfoTest {
    @Test
    public void testAddress() {
        BuddyInfo buddyInfo = new BuddyInfo();
        buddyInfo.setAddress("address");

        assertEquals("address", buddyInfo.getAddress());
    }
}
