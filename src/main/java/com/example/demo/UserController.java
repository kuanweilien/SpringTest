package com.example.demo;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    /*
    private ArrayList<UserModel> employeeList = new ArrayList<UserModel>();

    @GetMapping("/users")
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
    @Autowired
    private UserService userService;
    //*/
    //*
    @GetMapping()
    public Iterable<UserModel> getAllEmployees() 
    {
        return employeeRepository.findAll();
    }
    //*/
    //*
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getEmployeeById(
        @PathVariable(value = "id") 
        Long employeeId)
        throws ResourceNotFoundException 
    {
        UserModel employee = employeeRepository.findById(employeeId)
                                                .orElseThrow(() -> 
                                                new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping()
    public UserModel createEmployee(
        @Valid 
        @RequestBody 
        UserModel employee) 
    {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity < UserModel > updateEmployee(
        @PathVariable(value = "id") 
        Long employeeId,
        @Valid 
        @RequestBody
        UserModel employeeDetails) 
        throws ResourceNotFoundException 
    {
        UserModel employee = employeeRepository.findById(employeeId)
                                                .orElseThrow(() -> 
                                                new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setId(employeeDetails.getId());
        employee.setUserName(employeeDetails.getUserName());
        employee.setSex(employeeDetails.getSex());
        employee.setBirthday(employeeDetails.getBirthday());
        final UserModel updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @PutMapping("/name/{id}")
    public void updateEmployeeName(
        @PathVariable(value = "id")
        Long id,
        @RequestParam(required = false)
        String name
    )
    {
        userService.setUserName(id, name);

    }


    @DeleteMapping("/{id}")
    public Map<String,Boolean> deleteEmployee(
            @PathVariable(value = "id") 
            Long employeeId)
            throws ResourceNotFoundException 
    {
        UserModel employee = employeeRepository.findById(employeeId)
                                                .orElseThrow(() -> 
                                                new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    //*/

    @GetMapping("/hello") 
	public List<String> hello(){
		return userService.getHelloWorld();
	}
    @GetMapping("/name/{name}") 
	public ResponseEntity<UserModel> findByName(
            @PathVariable(value = "name") 
            String name
        )throws ResourceNotFoundException 
    {
		UserModel user = employeeRepository.findByUserName(name)
                                            .orElseThrow(() -> 
                                            new ResourceNotFoundException("Employee not found for this name :: " + name));

        return ResponseEntity.ok().body( user);
	}
}