package module2.week2.week2module22.Services;

import module2.week2.week2module22.Dto.EmployeeDto;
import module2.week2.week2module22.Entity.EmployeeEntity;
import module2.week2.week2module22.Repository.EmployeeRepository;
import module2.week2.week2module22.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    //because using modelmapper
    //public EmployeeService(EmployeeRepository employeeRepository) {
      //  this.employeeRepository = employeeRepository;
    //}

    public Optional<EmployeeDto> getEmployeeById(Long id) {
        //EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //down line is comented because we have made config the model mapping
        //ModelMapper modelMapper = new ModelMapper();
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity , EmployeeDto.class));
    }

    public List<EmployeeDto> getEmployees() {
        List<EmployeeEntity>  employeeEntities= employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createEmployee(EmployeeDto inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    public EmployeeDto updateEmployeebyId(Long employeeId, EmployeeDto employeeDto) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    public void isExistsByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("employee not found with id: "  + employeeId);
    }

    public boolean deleteEmployeebyId(Long employeeId) {
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDto updatePartialEmployeebyId(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        BeanWrapper wrapper = new BeanWrapperImpl(employeeEntity);
        updates.forEach((field, value) -> {
            wrapper.setPropertyValue(field, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }
}
