package bev0802.hw_4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeDirectory {   

    private Integer serviceNumber;
    private Integer phoneNumber;
    private String name;
    private Integer experience;

    public EmployeeDirectory(Integer serviceNumber, Integer phoneNumber, String name, Integer experience) {
        this.serviceNumber = serviceNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }    

    @Override
    public String toString() {
        return "\n" + "табельный номер: " + serviceNumber + ", Имя: " + name 
                    + ", Номер телефона: " + phoneNumber + ", стаж: " + experience + " лет";
    }

    public Integer getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(Integer serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    
    // Метод, который создает справочник сотрудника и записывает его в файл
    public static void createEmployeeDirectory(List<EmployeeDirectory> employeeDirectories, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (EmployeeDirectory employee : employeeDirectories) {
                writer.write(employee.toString() + "\n");
            }
            System.out.println("Справочник сотрудников успешно создан и записан в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при создании и записи справочника сотрудников в файл");
            e.printStackTrace();
        }
    }

    
    //Метод, который ищет сотрудника по стажу (может быть список)   
    public static List<EmployeeDirectory> findEmployeesByExperience(Integer experience, List<EmployeeDirectory> employeeDirectories) {
        return employeeDirectories.stream()
                .filter(employee -> employee.getExperience() == experience)
                .collect(Collectors.toList());
    }    
    
    //Метод, который ищет сотрудника по имени (может быть список)
    public static List<EmployeeDirectory> findEmployeesByName(String name, List<EmployeeDirectory> employeeDirectories) {
        return employeeDirectories.stream()
                .filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
    }
    
    // Метод, который ищет сотрудника по табельному номеру (может быть список)
    public static List<EmployeeDirectory> findEmployeesByServiceNumber(int serviceNumber, List<EmployeeDirectory> employeeDirectories) {
        return employeeDirectories.stream()
                .filter(employee -> employee.getServiceNumber() == serviceNumber)
                .collect(Collectors.toList());
    }    
    
    // Метод, который добавляет нового сотрудника в справочник через ввод с терминала и добавляет его в список в файл.
    public static void addEmployeeFromTerminal(List<EmployeeDirectory> employeeDirectories) {
        Scanner scanner = new Scanner(System.in, "Cp866");
        
        System.out.println("Введите табельный номер сотрудника:");
        int serviceNumber = scanner.nextInt();
        
        System.out.println("Введите номер телефона сотрудника:");
        int phoneNumber = scanner.nextInt();
        
        scanner.nextLine(); // consume the newline character
        
        System.out.println("Введите имя сотрудника:");
        String name = scanner.nextLine();
        
        System.out.println("Введите стаж сотрудника:");
        Integer experience = scanner.nextInt();
        
        EmployeeDirectory newEmployee = new EmployeeDirectory(serviceNumber, phoneNumber, name, experience);
        employeeDirectories.add(newEmployee);

        scanner.close();                
    }

    
}