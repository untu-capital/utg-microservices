package com.untucapital.purchaseorder.controller;


import com.untucapital.purchaseorder.dto.req.DepartmentRequestDTO;
import com.untucapital.purchaseorder.dto.res.DepartmentResponseDTO;
import com.untucapital.purchaseorder.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */

@RestController
@RequestMapping("/pos/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    //save department
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDTO saveDepartment(@RequestBody DepartmentRequestDTO department){
        return departmentService.saveDepartment(department);
    }
    //get department by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponseDTO getDepartmentById(@PathVariable Integer id){
        return departmentService.getDepartmentById(id);
    }
    //get all departments
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponseDTO> getAllDepartments(){

        return departmentService.getAllDepartments();
    }
    //update department
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponseDTO updateDepartment(@RequestBody DepartmentRequestDTO department){
        return departmentService.updateDepartment(department);
    }

    //delete department
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDepartment(id);
    }
}
