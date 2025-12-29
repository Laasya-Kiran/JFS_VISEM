package com.skillnext1;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.skillnext1.Student;
import com.skillnext1.HibernateUtil;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Insert Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1: {   // INSERT
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Enter Semester: ");
                    int sem = sc.nextInt();

                    System.out.print("Enter Department: ");
                    sc.nextLine();
                    String dept = sc.nextLine();

                    Student s = new Student(name, sem, dept);
                    session.save(s);

                    tx.commit();
                    session.close();
                    System.out.println("✅ Student inserted");
                    break;
                }

                case 2: {   // VIEW STUDENTS (OPTIONAL ASCENDING ORDER)
                    Session session = HibernateUtil.getSessionFactory().openSession();

                    System.out.print("Display names in ascending order? (1.Yes / 2.No): ");
                    int ch = sc.nextInt();

                    List<Student> list;

                    if (ch == 1) {
                        list = session.createQuery(
                                "from Student order by name asc", Student.class).list();
                    } else {
                        list = session.createQuery(
                                "from Student", Student.class).list();
                    } 

                    System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Sem", "Dept");

                    for (Student s : list) {
                        System.out.printf(
                        "%-5d %-20s %-5d %-10s%n",
                                s.getId(),
                                s.getName(),
                                s.getSem(),
                                s.getDept()
                        );
                    }
                    session.close();
                    break;
                }


                case 3: {   // UPDATE
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    Student s = session.get(Student.class, id);

                    if (s != null) {
                        System.out.print("Enter new Name: ");
                        sc.nextLine();
                        s.setName(sc.nextLine());

                        System.out.print("Enter new Semester: ");
                        s.setSem(sc.nextInt());

                        System.out.print("Enter new Department: ");
                        sc.nextLine();
                        s.setDept(sc.nextLine());

                        session.update(s);
                        tx.commit();
                        System.out.println("✅ Student updated");
                    } else {
                        System.out.println("❌ Student not found");
                    }

                    session.close();
                    break;
                }

                case 4: {   // DELETE
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    Student s = session.get(Student.class, id);

                    if (s != null) {
                        session.delete(s);
                        tx.commit();
                        System.out.println("✅ Student deleted");
                    } else {
                        System.out.println("❌ Student not found");
                    }

                    session.close();
                    break;
                }

                case 5:
                    sc.close();
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }
    }
}
