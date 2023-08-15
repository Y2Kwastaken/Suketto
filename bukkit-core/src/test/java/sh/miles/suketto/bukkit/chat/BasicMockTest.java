package sh.miles.suketto.bukkit.chat;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.MockPlugin;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BasicMockTest {

    protected ServerMock server;
    protected MockPlugin plugin;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
        plugin = MockBukkit.createMockPlugin();
    }

    @AfterEach
    public void teardown() {
        MockBukkit.unmock();
    }


}
