package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.*;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of the AreaFinder interface that uses a graph to find a closed area.
 *
 * @param <L> location type
 */
public class GraphAreaFinder<L, C extends Cursor<L>> implements AreaFinder<L> {

    private Map<Node, List<Node>> graph;
    private Deque<L> possibleAreaVertices;
    private List<L> areaVertices;
    private final Panel<L, C> panel;
    private Area<L> area;

    /**
     * Constructor method.
     *
     * @param panel the panel on which to search for a closed area
     * @throws NullPointerException if the panel is null
     */
    public GraphAreaFinder(Panel<L, C> panel) {
        this.panel = Objects.requireNonNull(panel, "The panel cannot be null");
        this.graph = new HashMap<>();
        this.possibleAreaVertices = new LinkedList<>();
        this.areaVertices = new ArrayList<>();
        this.area = null;
    }

    @Override
    public Optional<Area<L>> findClosedArea() {
        initializeGraph();
        DFS();
        return this.area == null ? Optional.empty() : Optional.of(area);
    }

    /**
     * Adds all lines of the panel to the graph,
     * a line is an edge of the graph in which the extremes are the nodes.
     */
    private void initializeGraph() {
        this.possibleAreaVertices = new LinkedList<>();
        this.areaVertices = new ArrayList<>();
        this.graph = new HashMap<>();
        this.area = null;
        for (Line<L> line : this.panel.getLines()) {
            Node start = containsLabel(line.getStart()).orElse(null);
            Node end = containsLabel(line.getFinish()).orElse(null);
            if (start == null) {
                start = new Node(line.getStart());
                this.graph.put(start, new ArrayList<>());
            }
            if (end == null) {
                end = new Node(line.getFinish());
                this.graph.put(end, new ArrayList<>());
            }
            this.graph.get(start).add(end);
            this.graph.get(end).add(start);
        }
    }

    /**
     * Calls DFSVisit on all white nodes.
     */
    private void DFS() {
        for (Node node : this.graph.keySet())
            if (node.getColor() == Color.WHITE)
                DFSVisit(node, node);
    }

    /**
     * Calls DFSVisit recursively on all white nodes found, if a gray node
     * is found and it is not the parent of the current node then a cycle
     * is found and consequently a closed area is found.
     */
    private void DFSVisit(Node node, Node parent) {
        node.setColor(Color.GREY);
        if (this.area == null)
            this.possibleAreaVertices.add(node.label);
        for (Node adjNode : this.graph.get(node)) {
            if (adjNode.getColor() == Color.GREY && !adjNode.equals(parent))
                backtrack(adjNode);
            if (adjNode.getColor() == Color.WHITE)
                DFSVisit(adjNode, node);
        }
        node.setColor(Color.BLACK); //all adjacent nodes have been visited
        if (this.area == null)
            this.possibleAreaVertices.remove(node.label);
    }

    /**
     * Performs a backtrack of the nodes until it finds the node given as parameter.
     */
    private void backtrack(Node node) {
        L label;
        do {
            label = this.possibleAreaVertices.removeLast();
            this.areaVertices.add(label);
        } while (!label.equals(node.getLabel()));
        makeArea();
    }

    /**
     * Creates an area with the vertices of the area.
     */
    private void makeArea() {
        List<List<L>> pairVertices = pairAreaVertices();
        List<Line<L>> areaLines = new ArrayList<>();
        for (List<L> pair : pairVertices) {
            Line<L> panelLine = this.panel.getLine(pair.get(0), pair.get(1));
            areaLines.add(new BasicLine<>(pair.get(0), pair.get(1), panelLine.getSize(), panelLine.getColor()));
        }
        this.area = new BasicArea<>(areaLines, this.panel.getFillColor());
    }

    /**
     * Separates vertices into groups of two
     */
    private List<List<L>> pairAreaVertices() {
        List<List<L>> pairs = new ArrayList<>();
        for (int i = 0; i < this.areaVertices.size() - 1; i++)
            pairs.add(List.of(this.areaVertices.get(i), this.areaVertices.get(i + 1)));
        pairs.add(List.of(this.areaVertices.get(this.areaVertices.size() - 1), this.areaVertices.get(0)));
        return pairs;
    }

    /**
     * Determines if a node of the graph contains the label given as parameter.
     */
    private Optional<Node> containsLabel(L label) {
        return this.graph.keySet().stream().
                filter(node -> node.getLabel().equals(label)).
                findAny();
    }

    /**
     * Class used to represent a node of the graph.
     * To determine if a node is equal to another node, their labels are used.
     * White node : not yet visited.
     * Grey node : partially visited.
     * Black node : totally visited.
     */
    private class Node {
        L label;
        Color color;

        private Node(L label) {
            this.label = Objects.requireNonNull(label);
            this.color = Color.WHITE;
        }

        private void setColor(Color color) {
            this.color = Objects.requireNonNull(color);
        }

        private Color getColor() {
            return this.color;
        }

        private L getLabel() {
            return this.label;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return this.label.equals(((Node) o).getLabel());
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

    }

    private enum Color {
        WHITE, GREY, BLACK
    }
}
