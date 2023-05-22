import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    void testMain() {
        assertTimeout(Duration.ofSeconds(22), () -> {
            Main.main(new String[0]);
        });
    }
}