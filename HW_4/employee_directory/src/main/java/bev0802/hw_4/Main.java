package bev0802.hw_4;

import java.util.ArrayList;
import java.util.List;
import bev0802.hw_4.EmployeeDirectory.*;


public class Main {
    public static void main(String[] args) {
        List<EmployeeDirectory> employeeDirectories = new ArrayList();
        employeeDirectories.add(new EmployeeDirectory(1, 123456, "Иванов Иван Иванович", 5));
        employeeDirectories.add(new EmployeeDirectory(2, 654321, "Петров Петр Петрович", 10));
        employeeDirectories.add(new EmployeeDirectory(3, 987654, "Сидоров Сидор Сидорович", 5));
        employeeDirectories.add(new EmployeeDirectory(4, 456789, "Васильев Василий Васильевич", 7));
        employeeDirectories.add(new EmployeeDirectory(5, 321654, "Александров Александр Александрович", 2));        

        EmployeeDirectory.createEmployeeDirectory(employeeDirectories, "employees.txt");
        System.out.println(employeeDirectories);      

        List<EmployeeDirectory> filterEmployeesByExperience = EmployeeDirectory.findEmployeesByExperience(5, employeeDirectories);
        System.out.println(filterEmployeesByExperience);

        List<EmployeeDirectory> filterEmployeesByName = EmployeeDirectory.findEmployeesByName("Иванов Иван Иванович", employeeDirectories);
        System.out.println(filterEmployeesByName);

        List<EmployeeDirectory> filterEmployeesByServiceNumber = EmployeeDirectory.findEmployeesByServiceNumber(4, employeeDirectories);
        System.out.println(filterEmployeesByServiceNumber);

        EmployeeDirectory.addEmployeeFromTerminal(employeeDirectories);     
        System.out.println(employeeDirectories);

    }   
        
}