package com.api.example.service;

import com.api.example.dto.EmployeeDto;
import com.api.example.entity.Employee;
import com.api.example.exceptions.ResourceNotFound;
import com.api.example.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public String saveEmployee(EmployeeDto employeeDto){
        Employee e=new Employee();
        BeanUtils.copyProperties(employeeDto,e);
        try {
            employeeRepository.save(e);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return "done";
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto empDto=new EmployeeDto();
        BeanUtils.copyProperties(savedEmployee,empDto);
        return empDto;
    }

    public List<Employee> getAllEmployees(int pageNo, int pageSize, String discription, String dir) {
        Sort sort=dir.equalsIgnoreCase("asc")?Sort.by(discription).ascending():Sort.by(discription).descending();
        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(pageable);
        List<Employee> employees = all.getContent();
        return employees;
    }

    public Employee getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Record not found")
        );
        return employee;
    }
}
