import java.util.*;
import java.util.stream.*;

class Employee {
     String name;
     int age;
     String gender;
     double salary;
     String designation;
     String department;

    public Employee(String name, int age, String gender, double salary,
                    String designation, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }
    

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getSalary() { return salary; }
    public String getDesignation() { return designation; }
    public String getDepartment() { return department; }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

public class StreamAssignment {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

      
             employees.add(new Employee("Amit", 45, "Male", 90000, "Manager", "HR"));
             employees.add(new Employee("Ravi", 38, "Male", 75000, "Developer", "IT"));
             employees.add(new Employee("Sneha", 30, "Female", 65000, "Developer", "IT"));
             employees.add(new Employee("Neha", 42, "Female", 88000, "Manager", "Finance"));
             employees.add(new Employee("Ankit", 28, "Male", 55000, "Tester", "IT"));
             employees.add(new Employee("Pooja", 35, "Female", 72000, "Analyst", "Finance"));
             employees.add(new Employee("Rahul", 50, "Male", 95000, "Director", "Admin"));
             employees.add(new Employee("Kiran", 26, "Female", 48000, "HR Executive", "HR"));
             employees.add(new Employee("Suresh", 41, "Male", 82000, "Manager", "IT"));
             employees.add(new Employee("Meena", 33, "Female", 60000, "Developer", "IT"));
        

        // 1. Highest salary paid employee
        Employee highestSalaryEmployee =
                employees.stream()
                         .max(Comparator.comparing(Employee::getSalary))
                         .get();

        System.out.println("Highest Salary Paid Employee: " +
                highestSalaryEmployee.getName());

        // 2. Count male & female employees
        Map<String, Long> genderCount =
                employees.stream()
                         .collect(Collectors.groupingBy(
                                 Employee::getGender,
                                 Collectors.counting()));

        System.out.println("Male & Female Count: " + genderCount);
          // better use partitioningBy rather than groupingBy

        // 3. Total expense department-wise
        Map<String, Double> departmentExpense =
                employees.stream()
                         .collect(Collectors.groupingBy(
                                 Employee::getDepartment,
                                 Collectors.summingDouble(Employee::getSalary)));

        System.out.println("Department Wise Expense: " + departmentExpense);

      

        // 4. Top 5 senior employees
        System.out.println("Top 5 Senior Employees:");
        employees.stream()
                 .sorted(Comparator.comparing(Employee::getAge).reversed())
                 .limit(5)
                 .forEach(e ->
                         System.out.println(e.getName() + " - " + e.getAge()));

        // 5. Names of all managers
        System.out.println("Managers:");
        employees.stream()
                 .filter(e -> e.getDesignation().equalsIgnoreCase("Manager"))
                 .map(Employee::getName)
                 .forEach(System.out::println);

        // 6. Hike salary by 20% except managers
        employees.stream()
                 .filter(e -> !e.getDesignation().equalsIgnoreCase("Manager"))
                 .forEach(e -> e.setSalary(e.getSalary() * 1.20));


   //For 5 and 6 we have  some better performance reusable approach 
   /*
        list.stream().filter(p1)
        list.stream().filter(p1.negate()).map(s->s*1.2)
   
   */
        // 7. Total number of employees
        long totalEmployees = employees.stream().count();
        System.out.println("Total Employees: " + totalEmployees);
    }
}
 // better we can use  list.size(); 

 
