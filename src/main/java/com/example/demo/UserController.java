package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    /*
    private ArrayList<UserModel> employeeList = new ArrayList<UserModel>();

    @GetMapping("/employees")
    public ArrayList<UserModel> getAllEmployees() {
        UserModel user = new UserModel();
        user.setId(10L);
        user.setUser_name("Ben");
        
        employeeList.add(user);
        return employeeList;
    }
    //*/

    //*
    @Autowired
    private UserRepository employeeRepository;
    //*/
    //*
    @GetMapping("/employees")
    public Iterable < UserModel > getAllEmployees() {
        return employeeRepository.findAll();
    }
    //*/
    //*
    @GetMapping("/employees/{id}")
    public ResponseEntity < UserModel > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        UserModel employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public UserModel createEmployee(@Valid @RequestBody UserModel employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < UserModel > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody UserModel employeeDetails) throws ResourceNotFoundException {
            UserModel employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setId(employeeDetails.getId());
        employee.setUser_name(employeeDetails.getUser_name());
        employee.setSex(employeeDetails.getSex());
        employee.setBirthday(employeeDetails.getBirthday());
        final UserModel updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        UserModel employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    //*/
}