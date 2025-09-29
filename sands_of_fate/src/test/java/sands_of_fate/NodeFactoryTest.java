package sands_of_fate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.javarush.crivoi.quest.builder.NodeFactory;
import com.javarush.crivoi.quest.model.game.Node;

class NodeFactoryTest {

    @Test
    void testCreateNodes() {
        Map<String, Node> nodes = NodeFactory.createNodes();

        // Check that all expected nodes exist
        assertTrue(nodes.containsKey("1"));
        assertTrue(nodes.containsKey("2A"));
        assertTrue(nodes.containsKey("3C"));
        assertTrue(nodes.containsKey("END4"));

        // Check that node "1" has 3 options
        Node node1 = nodes.get("1");
        assertEquals(3, node1.getOptions().size());
        assertEquals("2A", node1.getOptions().get(0).getIdNextNode());
        assertEquals("2B", node1.getOptions().get(1).getIdNextNode());
        assertEquals("2C", node1.getOptions().get(2).getIdNextNode());

        // Check an ending node has no options
        Node end1 = nodes.get("END1");
        assertTrue(end1.getOptions().isEmpty());
    }
}
