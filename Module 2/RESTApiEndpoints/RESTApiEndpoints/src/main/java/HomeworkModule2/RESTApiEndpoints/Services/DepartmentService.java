package HomeworkModule2.RESTApiEndpoints.Services;

import HomeworkModule2.RESTApiEndpoints.Dto.DepartmentDto;
import HomeworkModule2.RESTApiEndpoints.Entity.DepartmentEntity;
import HomeworkModule2.RESTApiEndpoints.Repository.DepartmentRepository;
import HomeworkModule2.RESTApiEndpoints.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository , ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    //For get
    public List<DepartmentDto> getDepartments() {
        List<DepartmentEntity> departmentEntity = departmentRepository.findAll();
        return departmentEntity
                .stream()
                .map(Entities -> modelMapper.map(Entities , DepartmentDto.class))
                .collect(Collectors.toList());
    }

    //For Post
    public DepartmentDto createDepartment(DepartmentDto NewDepartmentDto) {
        DepartmentEntity toCreateDepartment =  modelMapper.map(NewDepartmentDto ,  DepartmentEntity.class);
        DepartmentEntity SavedDepartment = departmentRepository.save(toCreateDepartment);
        return modelMapper.map(SavedDepartment , DepartmentDto.class);
    }

    //For Put
    public DepartmentDto updateDepartment(Long departmentId , DepartmentDto NewDepartmentDto) {
        DepartmentEntity toUpdateDepartment = modelMapper.map(NewDepartmentDto ,  DepartmentEntity.class);
        //here we manually connect departmentId to id
        toUpdateDepartment.setId(departmentId);
        DepartmentEntity SavedDepartment = departmentRepository.save(toUpdateDepartment);
        return modelMapper.map(SavedDepartment , DepartmentDto.class);

    }

    //For Delete

    public void isExists(Long departmentId){
        boolean exist = departmentRepository.existsById(departmentId);
        if(!exist) throw new ResourceNotFoundException("department not fund");
    }

    public boolean deleteDepartment(Long departmentId){
        isExists(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    //For patch(means only when you have to change one string or variable)
    public DepartmentDto updatePartialDepartment(Long departmentId, Map<String, Object> update) {
        DepartmentEntity existing = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department not fund"));
        //DepartmentEntity toUpdateDepartment = modelMapper.map(update , DepartmentEntity.class);
        //above will create a new department with new id
        //using bean wrapper it dynamically update the partial value
        modelMapper.map(update, existing);
        DepartmentEntity updatedDepartment = departmentRepository.save(existing);
        return modelMapper.map(updatedDepartment , DepartmentDto.class);
    }

    public Optional<DepartmentDto> getDepartmentsById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity , DepartmentDto.class));
    }
}
