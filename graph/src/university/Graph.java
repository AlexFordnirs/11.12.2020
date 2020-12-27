package university;

import java.util.ArrayDeque;
import java.util.Queue;

public class Graph {

    private final int MAX_SIZE = 10;
    private Vertex vertices[];
    int adjMatrix[][];
    private int size;

    public Graph() {
        vertices = new Vertex[MAX_SIZE];
        adjMatrix = new int[MAX_SIZE][MAX_SIZE];
        size = 0;
    }

    public boolean addVertex(char label) {
        if (size < MAX_SIZE) {
            vertices[size++] = new Vertex(label);
            return true;
        }
        return false;
    }

    public boolean addEdge(int start, int finish) {
        if (start > -1 && finish > -1 && start < size && finish < size) {
            adjMatrix[start][finish] = 1;
            adjMatrix[finish][start] = 1;
            return true;
        } else
            return false;
    }

    public boolean addEdge(int start, int finish, int weight) {
        if (start > -1 && finish > -1 && start < size && finish < size) {
            adjMatrix[start][finish] = weight;
            adjMatrix[finish][start] = weight;
            return true;
        } else
            return false;
    }

    public int[] getMinPaths(int start) {
        int[] distance = new int[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;

        for (int k=0;k<size;k++) {
            //обойти смежніе
            for (int i = 0; i < size; i++) {
                if (adjMatrix[start][i] != 0 && !vertices[i].isVisited()) {
                    //если не посещена - анализ пути
                    if (distance[i] > (adjMatrix[start][i] + distance[start])) {
                        distance[i] = adjMatrix[start][i] + distance[start];
                    }
                }
            }

            //пометить посещение
            vertices[start].setVisited(true);
            //найти минимальную - перейти в ней
            int min = Integer.MAX_VALUE;
            int indexMin = 0;
            for (int i = 0; i < size; i++) {
                if (adjMatrix[start][i] != 0 && !vertices[i].isVisited() && adjMatrix[start][i] < min) {
                    min = adjMatrix[start][i];
                    indexMin = i;
                }
            }
            start=indexMin;
        }


        return distance;
    }

    public int getAdjVertex(int start) {
        if (start > -1 && start < size) {
            for (int i = 0; i < size; i++) {
                if (adjMatrix[start][i] == 1 && !vertices[i].isVisited()) {
                    vertices[i].setVisited(true);
                    return i;
                }
            }
        }
        return -1;
    }

    public void goW(int start) {
        System.out.println(start + " :  " + vertices[start].getLabel());
        vertices[start].setVisited(true);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int tmp = queue.remove();
            int num;
            while ((num = getAdjVertex(tmp)) != -1) {
                System.out.println(start + " :  " + vertices[num].getLabel());
                vertices[num].setVisited(true);
                queue.add(num);
            }


        }
    }

    public void falseVisited() {
        for (int i = 0; i < size; i++) {
            vertices[i].setVisited(false);

        }
    }

    public void searchDepth(int current) {

        System.out.println(current + " " + vertices[current].getLabel());
        vertices[current].setVisited(true);

        Queue<Integer> queue = new ArrayDeque<>();
        int num;
        while ((num = getAdjVertex(current)) != -1) {
            queue.add(num);
            vertices[num].setVisited(true);
        }

        while (!queue.isEmpty()) {
            searchDepth(queue.poll());

        }
    }

    public void searchWidth(int start) {
        System.out.println(start + " " + vertices[start].getLabel());
        vertices[start].setVisited(true);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            int num;
            while ((num = getAdjVertex(tmp)) != -1) {
                System.out.println(start + " " + vertices[num].getLabel());
                vertices[num].setVisited(true);
                queue.add(num);
            }
        }
        falseVisited();
    }
    public  void AddArc(int start,int finish)
    {
        adjMatrix[start][finish]=1;
    }
    public void topologySort(int start)
    {
        System.out.println(start+" ");
        vertices[start].setVisited(true);
        int end;
        while ((end=getAdjVertex(start))!=-1)
        {
            topologySort(end);
        }
    }

}
