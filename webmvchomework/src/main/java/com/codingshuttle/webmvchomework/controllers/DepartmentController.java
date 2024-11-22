package com.codingshuttle.webmvchomework.controllers;

import com.codingshuttle.webmvchomework.dto.DepartmentDTO;
import com.codingshuttle.webmvchomework.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentByID(@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.getDepartmentByID(departmentId));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentService.createNewDepartment(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public  ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable Long departmentId,@RequestBody @Valid DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentId,departmentDTO));
    }

    @PatchMapping("/{departmentId}")
    public  ResponseEntity<DepartmentDTO> updatePartialDepartmentById(@PathVariable Long departmentId,@RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(departmentService.updatePartialDepartmentById(departmentId,updates));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        boolean gotDeleted = departmentService.deleteDepartmentById(departmentId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

}
