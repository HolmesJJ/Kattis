package holmesjj.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Node {
    int row;
    int col;
    List<Node> neighbors;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        neighbors = new ArrayList<>();
    }
}

public class Main {

    public static int getStars(int row, int col, BufferedReader br) throws IOException {

        // 邻接表建图
        Node[][] adjList = new Node[row][col];
        for (int i = 0; i < row; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                // 有星星时
                if (chars[j] == '-') {
                    Node node = new Node(i, j);
                    adjList[i][j] = node;
                    // 上
                    if (i > 0) {
                        if (adjList[i - 1][j] != null) {
                            Node nNode = adjList[i - 1][j];
                            node.neighbors.add(nNode);
                            nNode.neighbors.add(node);
                        }
                    }
                    // 下
                    if (i < row - 1) {
                        if (adjList[i + 1][j] != null) {
                            Node nNode = adjList[i + 1][j];
                            node.neighbors.add(nNode);
                            nNode.neighbors.add(node);
                        }
                    }
                    // 左
                    if (j > 0) {
                        if (adjList[i][j - 1] != null) {
                            Node nNode = adjList[i][j - 1];
                            node.neighbors.add(nNode);
                            nNode.neighbors.add(node);
                        }
                    }
                    // 右
                    if (j < col - 1) {
                        if (adjList[i][j + 1] != null) {
                            Node nNode = adjList[i][j + 1];
                            node.neighbors.add(nNode);
                            nNode.neighbors.add(node);
                        }
                    }
                }
            }
        }

        // DFS遍历全图
        int stars = 0;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        Stack<Node> sn = new Stack<>();
        for (int i = 0; i < adjList.length; i++) {
            for (int j = 0; j < adjList[i].length; j++) {
                Node sNode = adjList[i][j];
                if (sNode != null && !visited[sNode.row][sNode.col]) {
                    // DFS
                    sn.add(sNode);
                    visited[sNode.row][sNode.col] = true;
                    while (!sn.isEmpty()) {
                        Node node = sn.peek();
                        boolean allVisited = true;
                        for (int k = 0; k < node.neighbors.size(); k++) {
                            Node nNode = node.neighbors.get(k);
                            if (!visited[nNode.row][nNode.col]) {
                                sn.add(nNode);
                                visited[nNode.row][nNode.col] = true;
                                allVisited = false;
                            }
                        }
                        if (allVisited) {
                            sn.pop();
                        }
                    }
                    stars++;
                }
            }
        }
        return stars;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 1;
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("")) {
                break;
            }
            String[] rowCol = line.split(" ");
            int row = Integer.parseInt(rowCol[0]);
            int col = Integer.parseInt(rowCol[1]);
            int stars = getStars(row, col, br);
            System.out.println("Case " + caseNum + ": " + stars);
            caseNum++;
        }
        br.close();
    }
}
