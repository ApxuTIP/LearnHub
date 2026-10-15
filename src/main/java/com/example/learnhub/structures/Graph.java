package com.example.learnhub.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public static class Edge {
        private final int destination;
        private final int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{destination=" + destination + ", weight=" + weight + "}";
        }
    }

    private final Map<Integer, List<Edge>> adjacency = new HashMap<>();
    private final List<Integer> vertices = new ArrayList<>();

    public void addVertex(int vertex) {
        if (!adjacency.containsKey(vertex)) {
            adjacency.put(vertex, new ArrayList<>());
            vertices.add(vertex);
        }
    }

    public void addEdge(int sourceVertex, int destinationVertex, int weight) {
        addVertex(sourceVertex);
        addVertex(destinationVertex);
        adjacency.get(sourceVertex).add(new Edge(destinationVertex, weight));
    }

    public List<Edge> getNeighbors(int vertex) {
        List<Edge> edges = adjacency.get(vertex);
        if (edges == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(edges);
    }

    public List<Integer> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int edgeCount = 0;
        for (List<Edge> edges : adjacency.values()) {
            edgeCount += edges.size();
        }
        return edgeCount;
    }
}