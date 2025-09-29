package sands_of_fate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.javarush.crivoi.quest.model.game.Game;
import com.javarush.crivoi.quest.model.game.Node;

class GameTest {

    @Test
    void testGameInitialization() {
        Game game = new Game();
        assertNotNull(game.getNodes(), "Game nodes should be initialized");
        assertTrue(game.getNodes().containsKey("1"), "Start node '1' should exist");
    }

    @Test
    void testNodeNavigation() {
        Game game = new Game();
        Node start = game.getNodes().get("1");
        Node next = game.getNodes().get(start.getOptions().get(0).getIdNextNode());

        assertNotNull(next, "Next node should exist");
        assertTrue(next.getText() != null && !next.getText().isEmpty(), "Next node should have text");
    }
}
