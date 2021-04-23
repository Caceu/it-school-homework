import java.lang.*;
import java.util.*;

class Student implements Comparable<Student>{
    int rollno, id;
    private static int amount = 0;
    String name, address;

    public Student(int rollno, String name, String address)
    {
        this.rollno = rollno;
        this.name = name;
        this.address = address;
        this.id = amount;
        amount++;
    }

    public String toString()
    {
        return "#" + this.id + " " + this.rollno + " " + this.name + " "
                + this.address;
    }

    @Override
    public int compareTo(Student o) {
        return this.id - o.id;
    }
}

class Sortbyroll implements Comparator<Student> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Student a, Student b)
    {
        return a.rollno - b.rollno;
    }
}

class Sortbyname implements Comparator<Student> {
    // Used for sorting in ascending order of
    // name
    public int compare(Student a, Student b)
    {
        return a.name.compareTo(b.name);
    }
}

public class Main {
    public static void main(String[] args)
    {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        studentsList.add(new Student(111, "bbbb", "london"));
        studentsList.add(new Student(131, "aaaa", "nyc"));
        studentsList.add(new Student(121, "cccc", "jaipur"));

        System.out.println("Unsorted");
        for (Student item : studentsList) System.out.println(item);

        studentsList.sort(new Sortbyroll());

        System.out.println("\nSorted by rollno");
        for (Student value : studentsList) System.out.println(value);

        studentsList.sort(new Sortbyname());

        System.out.println("\nSorted by name");
        for (Student student : studentsList) System.out.println(student);

        Collections.sort(studentsList);
        System.out.println("\nSorted by id (as Comparable)");
        for (Student student : studentsList) System.out.println(student);
        
        Student[] studentsArray = new Student[3];
        studentsArray[0] = new Student(111, "bbbb", "london");
        studentsArray[1] = new Student(131, "aaaa", "nyc");
        studentsArray[2] = new Student(121, "cccc", "jaipur");

        Arrays.sort(studentsArray);
        System.out.println("\nSorted by id (a   s array of Comparable)");
        for (Student student : studentsArray) System.out.println(student);
    }
}