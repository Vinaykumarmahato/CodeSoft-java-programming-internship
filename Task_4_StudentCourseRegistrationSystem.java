import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public boolean registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        }
        return false;
    }

    public void dropStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode +
               "\nTitle: " + title +
               "\nDescription: " + description +
               "\nSchedule: " + schedule +
               "\nAvailable Slots: " + availableSlots + "/" + capacity + "\n";
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
        }
    }

    public void dropCourse(Course course) {
        course.dropStudent();
        registeredCourses.remove(course);
    }

    @Override
    public String toString() {
        StringBuilder coursesList = new StringBuilder();
        for (Course course : registeredCourses) {
            coursesList.append(course.getTitle()).append(", ");
        }
        return "Student ID: " + studentID +
               "\nName: " + name +
               "\nRegistered Courses: " + (coursesList.length() > 0 ? coursesList.substring(0, coursesList.length() - 2) : "None") + "\n";
    }
}

public class Task_4_StudentCourseRegistrationSystem {
    private List<Student> students;
    private List<Course> courses;

    public Task_4_StudentCourseRegistrationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task_4_StudentCourseRegistrationSystem system = new Task_4_StudentCourseRegistrationSystem();

        // Add initial students and courses here
        system.addStudent(new Student(1, "John Doe"));
        system.addStudent(new Student(2, "Jane Smith"));
        system.addCourse(new Course("CS101", "Introduction to Programming", "Learn the basics of programming", 50, "Mon, Wed 9:00 AM - 10:30 AM"));
        system.addCourse(new Course("MATH202", "Advanced Mathematics", "Advanced math concepts", 40, "Tue, Thu 11:00 AM - 12:30 PM"));

        while (true) {
            System.out.println("Student Course Registration System");
            System.out.println("1. Register Course");
            System.out.println("2. Drop Course");
            System.out.println("3. Display Student Info");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Student student = system.findStudentByID(studentID);
                    Course course = system.findCourseByCode(courseCode);
                    if (student != null && course != null) {
                        student.registerCourse(course);
                        System.out.println("Course registered successfully.");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int studentIDDrop = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Code: ");
                    String courseCodeDrop = scanner.nextLine();
                    Student studentDrop = system.findStudentByID(studentIDDrop);
                    Course courseDrop = system.findCourseByCode(courseCodeDrop);
                    if (studentDrop != null && courseDrop != null) {
                        studentDrop.dropCourse(courseDrop);
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int studentIDInfo = scanner.nextInt();
                    Student studentInfo = system.findStudentByID(studentIDInfo);
                    if (studentInfo != null) {
                        System.out.println("Student Info:\n" + studentInfo);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting Student Course Registration System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
