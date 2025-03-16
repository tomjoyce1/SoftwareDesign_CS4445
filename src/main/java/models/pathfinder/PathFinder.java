package models.pathfinder;

import models.map.AirTrafficMap;

import java.util.*;

public class PathFinder {
    private final AirTrafficMap map;

    public PathFinder(AirTrafficMap map) {
        this.map = map;
    }

    public List<int[]> findShortestPath(int startRow, int startCol, int endRow, int endCol) {
        int rows = map.getRows();
        int cols = map.getCols();
        int[][] distances = initializeDistances(rows, cols, startRow, startCol);
        int[][][] previous = new int[rows][cols][2];
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[]{startRow, startCol, 0});
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            if (!visited[r][c]) {
                visited[r][c] = true;
                if (r == endRow && c == endCol) break;
                updateNeighbors(queue, distances, previous, directions, r, c);
            }
        }

        return constructPath(previous, distances, startRow, startCol, endRow, endCol);
    }

    private int[][] initializeDistances(int rows, int cols, int startRow, int startCol) {
        int[][] distances = new int[rows][cols];
        for (int[] row : distances) Arrays.fill(row, Integer.MAX_VALUE);
        distances[startRow][startCol] = 0;
        return distances;
    }

    private void updateNeighbors(PriorityQueue<int[]> queue, int[][] distances, int[][][] previous, int[][] directions, int r, int c) {
        int rows = distances.length;
        int cols = distances[0].length;
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                int weight = 1;
                if (distances[r][c] + weight < distances[nr][nc]) {
                    distances[nr][nc] = distances[r][c] + weight;
                    previous[nr][nc] = new int[]{r, c};
                    queue.add(new int[]{nr, nc, distances[nr][nc]});
                }
            }
        }
    }

    private List<int[]> constructPath(int[][][] previous, int[][] distances, int startRow, int startCol, int endRow, int endCol) {
        LinkedList<int[]> path = new LinkedList<>();
        if (distances[endRow][endCol] == Integer.MAX_VALUE) return path;
        for (int[] at = {endRow, endCol}; ; at = previous[at[0]][at[1]]) {
            path.addFirst(at);
            if (Arrays.equals(at, new int[]{startRow, startCol})) break;
        }
        return path;
    }
}