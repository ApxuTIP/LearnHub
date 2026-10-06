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
            for (CustomGraph.Edge edge : graph.neighbors(current)) {
                if (!visited.contains(edge.to)) {
                    visited.add(edge.to);
                    order.add(edge.to);
                    queue.addLast(edge.to);
                }
            }
        }
        return order;
    }

    public int countIndependentTracks() {
        Map<Integer, List<Integer>> undirected = buildUndirectedAdjacency();
        Set<Integer> visited = new HashSet<>();
        int tracks = 0;

        for (int vertex : graph.vertices()) {
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
        for (int vertex : graph.vertices()) {
            undirected.computeIfAbsent(vertex, key -> new ArrayList<>());
            for (CustomGraph.Edge edge : graph.neighbors(vertex)) {
                undirected.computeIfAbsent(edge.to, key -> new ArrayList<>()).add(vertex);
                undirected.get(vertex).add(edge.to);
            }
        }
        return undirected;
    }

    public List<Integer> findCycle() {
        Map<Integer, Integer> color = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        for (int vertex : graph.vertices()) {
            color.put(vertex, WHITE);
        }
        for (int vertex : graph.vertices()) {
            if (color.get(vertex) == WHITE) {
                List<Integer> cycle = dfsFindCycle(vertex, color, parent);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }
        return List.of();
    }

    private List<Integer> dfsFindCycle(int vertex,
                                       Map<Integer, Integer> color,
                                       Map<Integer, Integer> parent) {
        color.put(vertex, GRAY);
        for (CustomGraph.Edge edge : graph.neighbors(vertex)) {
            int next = edge.to;
            Integer nextColor = color.getOrDefault(next, WHITE);
            if (nextColor == GRAY) {
                return buildCyclePath(vertex, next, parent);
            }
            if (nextColor == WHITE) {
                parent.put(next, vertex);
                List<Integer> cycle = dfsFindCycle(next, color, parent);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }
        color.put(vertex, BLACK);
        return List.of();
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
            Integer temp = path.get(left);
            path.set(left, path.get(right));
            path.set(right, temp);
            left++;
            right--;
        }
        return path;
    }

    public Map<Integer, Integer> shortestHoursFrom(int sourceCourseId) {
        Map<Integer, Integer> distance = new HashMap<>();
        Set<Integer> settled = new HashSet<>();

        for (int vertex : graph.vertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(sourceCourseId, 0);

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (first, second) -> Integer.compare(first[0], second[0]));
        queue.add(new int[] {0, sourceCourseId});

        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int currentDistance = top[0];
            int currentVertex = top[1];

            if (settled.contains(currentVertex)) {
                continue;
            }
            settled.add(currentVertex);

            for (CustomGraph.Edge edge : graph.neighbors(currentVertex)) {
                if (settled.contains(edge.to)) {
                    continue;
                }
                int newDistance = currentDistance + edge.weight;
                Integer known = distance.get(edge.to);
                if (known == null || newDistance < known) {
                    distance.put(edge.to, newDistance);
                    queue.add(new int[] {newDistance, edge.to});
                }
            }
        }
        return distance;
    }

    public CustomGraph getGraph() {
        return graph;
    }
}