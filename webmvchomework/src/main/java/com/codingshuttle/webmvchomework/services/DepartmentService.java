package com.codingshuttle.webmvchomework.services;
import com.codingshuttle.webmvchomework.dto.DepartmentDTO;
import com.codingshuttle.webmvchomework.entities.DepartmentEntity;
import com.codingshuttle.webmvchomework.exceptions.ResourceNotFoundException;
import com.codingshuttle.webmvchomework.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO getDepartmentByID(Long departmentId) {
        existsDepartmentById(departmentId);
        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class)).get();
    }

    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();
        return departmentEntityList
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(@Valid DepartmentDTO deparmentDTO) {
        DepartmentEntity saveToBeEntity = modelMapper.map(deparmentDTO,DepartmentEntity.class);
        return modelMapper.map(departmentRepository.save(saveToBeEntity),DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long departmentId, @Valid DepartmentDTO deparmentDTO) {
        existsDepartmentById(departmentId);
        DepartmentEntity upateToBeEntity = modelMapper.map(deparmentDTO,DepartmentEntity.class);
        upateToBeEntity.setId(departmentId);
        return modelMapper.map(departmentRepository.save(upateToBeEntity),DepartmentDTO.class);
    }

    public DepartmentDTO updatePartialDepartmentById(Long departmentId, Map<String, Object> updates) {
        existsDepartmentById(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findFieldIgnoreCase(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntity,value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }

    public Boolean deleteDepartmentById(Long departmentId) {
        existsDepartmentById(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    private void existsDepartmentById(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department not found with id: "+departmentId);
    }
}
