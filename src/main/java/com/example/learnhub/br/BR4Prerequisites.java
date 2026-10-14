package com.example.learnhub.br;

import com.example.learnhub.structures.CustomGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class BR4Prerequisites {

    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    private final CustomGraph graph = new CustomGraph();

    public void addCourse(int courseId) {
        graph.addVertex(courseId);
    }

    public void addPrerequisite(int fromCourseId, int toCourseId, int hours) {
        graph.addEdge(fromCourseId, toCourseId, hours);
    }

    public List<Integer> reachableFrom(int startCourseId) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        visited.add(startCourseId);
        queue.addLast(startCourseId);

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();
            for (CustomGraph.Edge edge : graph.getNeighbors(current)) {
                int destination = edge.getDestination();
                if (!visited.contains(destination)) {
                    visited.add(destination);
                    order.add(destination);
                    queue.addLast(destination);
                }
            }
        }
        return order;
    }

    public int countIndependentTracks() {
        Map<Integer, List<Integer>> undirected = buildUndirectedAdjacency();
        Set<Integer> visited = new HashSet<>();
        int tracks = 0;

        for (int vertex : graph.getVertices()) {
            if (visited.contains(vertex)) {
                continue;
            }
            tracks++;
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.addLast(vertex);
            visited.add(vertex);
            while (!queue.isEmpty()) {
                int current = queue.removeFirst();
                List<Integer> neighbors = undirected.get(current);
                if (neighbors == null) {
                    continue;
                }
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.addLast(neighbor);
                    }
                }
            }
        }
        return tracks;
    }

    private Map<Integer, List<Integer>> buildUndirectedAdjacency() {
        Map<Integer, List<Integer>> undirected = new HashMap<>();
        for (int vertex : graph.getVertices()) {
            undirected.computeIfAbsent(vertex, key -> new ArrayList<>());
            for (CustomGraph.Edge edge : graph.getNeighbors(vertex)) {
                int destination = edge.getDestination();
                undirected.computeIfAbsent(destination, key -> new ArrayList<>()).add(vertex);
                undirected.get(vertex).add(destination);
            }
        }
        return undirected;
    }

    public List<Integer> findCycle() {
        Map<Integer, Integer> color = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int vertex : graph.getVertices()) {
            color.put(vertex, WHITE);
        }
        for (int vertex : graph.getVertices()) {
            if (color.get(vertex) == WHITE && result.isEmpty()) {
                result = dfsFindCycle(vertex, color, parent);
            }
        }
        return result;
    }

    private List<Integer> dfsFindCycle(int vertex,
                                       Map<Integer, Integer> color,
                                       Map<Integer, Integer> parent) {
        color.put(vertex, GRAY);
        List<Integer> cycle = new ArrayList<>();
        for (CustomGraph.Edge edge : graph.getNeighbors(vertex)) {
            int next = edge.getDestination();
            Integer nextColor = color.getOrDefault(next, WHITE);
            if (nextColor == GRAY) {
                cycle = buildCyclePath(vertex, next, parent);
                break;
            }
            if (nextColor == WHITE) {
                parent.put(next, vertex);
                cycle = dfsFindCycle(next, color, parent);
                if (!cycle.isEmpty()) {
                    break;
                }
            }
        }
        color.put(vertex, BLACK);
        return cycle;
    }

    private List<Integer> buildCyclePath(int from, int to, Map<Integer, Integer> parent) {
        List<Integer> path = new ArrayList<>();
        path.add(to);
        int current = from;
        while (current != to) {
            path.add(current);
            Integer previous = parent.get(current);
            if (previous == null) {
                break;
            }
            current = previous;
        }
        path.add(to);
        int left = 0;
        int right = path.size() - 1;
        while (left < right) {
            Integer temporary = path.get(left);
            path.set(left, path.get(right));
            path.set(right, temporary);
            left++;
            right--;
        }
        return path;
    }

    public Map<Integer, Integer> shortestHoursFrom(int sourceCourseId) {
        Map<Integer, Integer> distance = new HashMap<>();
        Set<Integer> settled = new HashSet<>();

        for (int vertex : graph.getVertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(sourceCourseId, 0);

        PriorityQueue<VertexDistance> queue = new PriorityQueue<>();
        queue.add(new VertexDistance(sourceCourseId, 0));

        while (!queue.isEmpty()) {
            VertexDistance currentPair = queue.poll();
            int currentVertex = currentPair.getVertex();

            if (settled.contains(currentVertex)) {
                continue;
            }
            settled.add(currentVertex);

            for (CustomGraph.Edge edge : graph.getNeighbors(currentVertex)) {
                int destination = edge.getDestination();
                if (settled.contains(destination)) {
                    continue;
                }
                int newDistance = currentPair.getDistance() + edge.getWeight();
                Integer known = distance.get(destination);
                if (known == null || newDistance < known) {
                    distance.put(destination, newDistance);
                    queue.add(new VertexDistance(destination, newDistance));
                }
            }
        }
        return distance;
    }

    public CustomGraph getGraph() {
        return graph;
    }

    private static class VertexDistance implements Comparable<VertexDistance> {

        private final int vertex;
        private final int distance;

        private VertexDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        private int getVertex() {
            return vertex;
        }

        private int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(VertexDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}