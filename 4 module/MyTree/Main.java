package tk.sasetz;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static PrintStream out = new PrintStream(System.out);
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Введите любые числа, чтобы заполнить дерево. 0 останавливает ввод чисел");
        int input = in.nextInt();
        MyTree<Integer> tree = new MyTree<>(input);
        if(input != 0)
            input = in.nextInt();
        while(input != 0){
            tree.add(input);
            input = in.nextInt();
        }
        out.println("Введите число для поиска:");
        out.println(tree.traverseWidth(in.nextInt()));
    }
}
