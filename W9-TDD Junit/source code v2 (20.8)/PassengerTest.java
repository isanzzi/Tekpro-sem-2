import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    Passenger ajay;
    @BeforeEach
    void setUp() {
        ajay = new Passenger("Azzar", true);
    }

    @Test
    void getName() {
        assertEquals("Belva", ajay.getName());
    }

    @Test
    void isVip() {
        assertEquals(false, ajay.isVip());
    }
}