package HomeworkModule2.RESTApiEndpoints.Controller;

import HomeworkModule2.RESTApiEndpoints.Dto.DepartmentDto;
import HomeworkModule2.RESTApiEndpoints.Repository.DepartmentRepository;
import HomeworkModule2.RESTApiEndpoints.Services.DepartmentService;
import HomeworkModule2.RESTApiEndpoints.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    //constructor injection of service layer
    public DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //get by employee id
    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<Optional<DepartmentDto>> getDepartmentById(@PathVariable("departmentId") Long departmentId) {
        Optional<DepartmentDto> departmentDto = departmentService.getDepartmentsById(departmentId);
        return departmentDto.map(DepartmentDto -> ResponseEntity.ok().body(departmentDto))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

    }

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    public DepartmentDto createDepartment(@RequestBody DepartmentDto NewDepartmentDto){
        return departmentService.createDepartment(NewDepartmentDto);
    }

    @PutMapping(path = "/{departmentId}")
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto UpdateDepartmentDto , @PathVariable(name = "departmentId") Long departmentId){
        return departmentService.updateDepartment(departmentId , UpdateDepartmentDto);
    }

    @DeleteMapping(path = "/{departmentId}")
    public boolean deleteDepartment(@PathVariable(name = "departmentId") Long departmentId){
        return departmentService.deleteDepartment(departmentId);
    }

    @PatchMapping(path ="/{departmentId}")
    public DepartmentDto updatepartialDepartment(@RequestBody Map<String , Object> update , @PathVariable("departmentId") Long departmentId){
        return departmentService.updatePartialDepartment(departmentId , update);
    }
}
