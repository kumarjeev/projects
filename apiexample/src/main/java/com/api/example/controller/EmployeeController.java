package com.api.example.controller;

import com.api.example.dto.APIResponse;
import com.api.example.dto.EmployeeDto;
import com.api.example.entity.Employee;
import com.api.example.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //http://localhost:8080/api/v1/employee/save
    @PostMapping("/save")
    public ResponseEntity<APIResponse<?>> saveEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
            ){
        if (result.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError e:fieldErrors){
                errors.put(e.getField(),e.getDefaultMessage());
            }
            APIResponse<HashMap<String, String>> response=new APIResponse<>();
            response.setMessage("done");
            response.setData(errors);
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String returnValue = employeeService.saveEmployee(employeeDto);
        if(returnValue != null){
            APIResponse<String> response=new APIResponse<>();
            response.setMessage("done");
            response.setData("Saved!!");
            response.setStatus(201);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
            APIResponse<String> response=new APIResponse<>();
            response.setMessage("done");
            response.setData("Duplicate entry!");
            response.setStatus(201);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    //Query Parameters

    //http://localhost:8080/api/v1/employee/delete/{id}
   // @DeleteMapping("/delete/{id}")// use for @PathVariable
    //http://localhost:8080/api/v1/employee/delete?id=
    @DeleteMapping("/delete") //use for @requestBody
    public ResponseEntity<APIResponse<String>> deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployee(id);
        APIResponse<String> response=new APIResponse<>();
        response.setMessage("done");
        response.setData("deleted");
        response.setStatus(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/employee/update?id=
    @PutMapping("/update")
    public ResponseEntity<APIResponse<EmployeeDto>> updateEmployee(@RequestParam long id,@RequestBody EmployeeDto
                                                              employeeDto){
        EmployeeDto empDto = employeeService.updateEmployee(id, employeeDto);
        APIResponse<EmployeeDto> response=new APIResponse();
        response.setMessage("update done");
        response.setData(empDto);
        response.setStatus(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/employee/allEmployees
    //http://localhost:8080/api/v1/employee/allEmployees?pageNo=2&pageSize=6
    @GetMapping("/allEmployees")
    public ResponseEntity<APIResponse<List<Employee>>> getAllEmployees(
            @RequestParam(name = "pageNo", required = false,defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize",required = false,defaultValue = "3") int pageSize,
            @RequestParam(name="discription",required = false,defaultValue = "asc") String discription,
            @RequestParam(name="dir",required = false,defaultValue = "asc") String dir
            ){
        List<Employee> allEmployees=employeeService.getAllEmployees(pageNo,pageSize,discription,dir);
        APIResponse<List<Employee>> response=new APIResponse();
        response.setMessage("All Employees");
        response.setData(allEmployees);
        response.setStatus(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/employee/findById?id=
    @GetMapping("/findById")
    public ResponseEntity<APIResponse<Employee>> getEmployeeById(@RequestParam long id){
        Employee employeeById = employeeService.getEmployeeById(id);
        APIResponse<Employee> response=new APIResponse();
        response.setMessage("Employee Details");
        response.setData(employeeById);
        response.setStatus(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
