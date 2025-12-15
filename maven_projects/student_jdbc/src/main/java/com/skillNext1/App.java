package com.skillNext1;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n====== Student Management System ======");
            System.out.println("1. Insert Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume extra newline

            try {
                switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter semester: ");
                    int sem = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter department: ");
                    String dept = sc.nextLine();

                    Student s = new Student(name, sem, dept);
                    dao.addStudent(s);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    List<Student> list = dao.getAllStudents();
                    System.out.println("\n--- Students List ---");
                    for (Student st : list) {
                        System.out.println(st);
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int upId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new semester: ");
                    int newSem = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new department: ");
                    String newDept = sc.nextLine();

                    Student updated = new Student(upId, newName, newSem, newDept);
                    dao.updateStudent(updated);
                    System.out.println("Student updated successfully!");
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int delId = sc.nextInt();
                    dao.deleteStudent(delId);
                    System.out.println("Student deleted successfully!");
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

