package module2.week2.week2module22.Controller;

import jakarta.validation.Valid;
import module2.week2.week2module22.Dto.EmployeeDto;
import module2.week2.week2module22.Services.EmployeeService;
import module2.week2.week2module22.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    //lec 2.3
    //private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //lec 2.3
    //public EmployeeController(EmployeeRepository employeeRepository) {
       // this.employeeRepository = employeeRepository;
    //}

    //lec 2.2
    //@GetMapping(path = "/SecretMessage")
    //public String SecretMessage() {
        //return "SecretMessage";
    //}

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<Optional<EmployeeDto>> getEmployeeById(@PathVariable(name = "employeeId") Long  id) {
        //return new EmployeeDto(employeeId , "Avanish" , "avanish@gmail.com" , 27 , LocalDate.of(2025,1,2) , true );
        Optional<EmployeeDto> employeeDto = employeeService.getEmployeeById(id);
        return employeeDto
                .map(EmplyeeDto -> ResponseEntity.ok(employeeDto))
                .orElseThrow(() -> new ResourceNotFoundException("employee not found with id: " + id));

    }

    @GetMapping
    public  List<EmployeeDto> getEmployees(@RequestParam(required = false , name = "inputAge") Integer age ) {
        //return "hi age is " + age;
        return employeeService.getEmployees();
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto inputEmployee) {
        //inputEmployee.setId(100L);
        //return inputEmployee;
        return employeeService.createEmployee(inputEmployee);
    }


    @PutMapping(path = "/{employeeId}" )
    public EmployeeDto updateEmployeebyId(@RequestBody @Valid EmployeeDto employeeDto , @PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.updateEmployeebyId(employeeId , employeeDto);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeebyId(@PathVariable Long employeeId ) {
        return employeeService.deleteEmployeebyId(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDto updatePartialEmployeebyId(@RequestBody Map<String , Object> updates , @PathVariable Long employeeId) {
        return employeeService.updatePartialEmployeebyId(employeeId , updates);
    }

}
