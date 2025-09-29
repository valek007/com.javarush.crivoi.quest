package sands_of_fate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.javarush.crivoi.quest.model.game.Node;
import com.javarush.crivoi.quest.model.game.Option;

class NodeTest {

    @Test
    void testNodeCreation() {
        Option opt1 = new Option("Go left", "2A");
        Option opt2 = new Option("Go right", "2B");
        Node node = new Node("1", "Start of the adventure", List.of(opt1, opt2));

        assertEquals("1", node.getId());
        assertEquals("Start of the adventure", node.getText());
        assertEquals(2, node.getOptions().size());
        assertEquals("Go left", node.getOptions().get(0).getText());
        assertEquals("2A", node.getOptions().get(0).getIdNextNode());
        assertEquals("Go right", node.getOptions().get(1).getText());
        assertEquals("2B", node.getOptions().get(1).getIdNextNode());
    }
}
