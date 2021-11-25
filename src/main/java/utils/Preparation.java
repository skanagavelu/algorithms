package utils;

import java.util.ArrayList;
import java.util.List;

public class Preparation {

    static class Student {

        public Student(String studentId, String name, int age) {

            this.studentId = studentId;
            this.age = age;
            this.name = name;
        }

        String studentId;
        int age;
        String name;
        char sex = 'M'; //'F/M'
        boolean isDayScholer = true;

        long joiningDate;

        @Override
        public String toString() {

            return "Student{" +
                   "studentId='" + studentId + '\'' +
                   ", age=" + age +
                   ", name='" + name + '\'' +
                   ", sex=" + sex +
                   ", isDayScholer=" + isDayScholer +
                   ", joiningDate=" + joiningDate +
                   '}';
        }
    }

    static class ClassSection {

        String sectionName;
        List<Student> studentList = new ArrayList<>();

        public ClassSection(String sectionName) {

            this.sectionName = sectionName;
        }

        public void addStudent(Student student) {

            studentList.add(student);
        }

        public List<Student> getStudentList() {

            return studentList;
        }

        @Override
        public String toString() {

            return "ClassSection{" +
                   "sectionName='" + sectionName + '\'' +
                   ", studentList=" + studentList +
                   '}';
        }
    }

    static class School {

        public School(String name) {

            this.name = name;
        }

        String name;
        List<ClassSection> sectionList = new ArrayList<>();

        @Override
        public String toString() {

            return "School{" +
                   "name='" + name + '\'' +
                   ", sectionList=" + sectionList +
                   '}';
        }
    }

    public static void main(String[] args) {

        School school = new School("SVN");
        ClassSection section_A = new ClassSection("A");
        ClassSection section_B = new ClassSection("B");

        school.sectionList.add(section_A);
        school.sectionList.add(section_B);

        Student vasanth = new Student("18csl002", "vasanth", 21);
        section_A.addStudent(vasanth);
        System.out.println("School: " + school);
    }

    interface Fruit {

        boolean isAcidic();

        boolean isBitter();

        List<String> getNutritions();
    }

    static class Apple implements Fruit {

        String colour;
        int size;

        String getColour() {

            return colour;
        }

        @Override
        public boolean isAcidic() {

            return true;
        }

        @Override
        public boolean isBitter() {

            return false;
        }

        @Override
        public List<String> getNutritions() {

            return null;
        }
    }

    class Circle {

        int radius;

        double getArea() {

            return 2 * 3.14 * radius;
        }

        int getRadius() {

            return radius;
        }
    }
}
