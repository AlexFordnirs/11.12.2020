package university;

import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    private static String[] action = new String[]{
            "Добавить новый путь",
            "Поиск в глубину",
            "Поиск в ширену",
            "Сортировка",
            "Выйти"
    };
    public static void main(String[] args) {
        // write your code here
        Graph graph = new Graph();
        int g=0;
        Scanner scanner=new Scanner(System.in);
        System.out.println("---------------------");
        boolean exit = false;
        while (!exit) {
            showChoices();
            int choice = scanner.nextInt() - 1;
            switch (choice) {
                case 0:

                        graph.addVertex((char)('A' + g));
                         g++;
                        out.println("Введите точку и связь");
                        try {
                            graph.addEdge(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
                            out.println("Элемент добавлен");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    break;
                case 1:
                    out.println("Введите элемент, в котором нужно начать");
                    graph.searchWidth(scanner.nextInt());
                    break;
                case 2:
                    out.println("Введите элемент, в котором нужно начать");
                    graph.searchDepth(scanner.nextInt());
                    break;
                case 3:
                    out.println("Введите элемент, в котором нужно начать");
                    graph.topologySort(scanner.nextInt());
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    break;
            }

        }

    }
    private static void showChoices() {
        out.println("Что вы хотите сделать?");
        for (int i = 0; i < action.length; i++)
            out.println(" " + (i + 1) + ". " + action[i]);
    }
}
