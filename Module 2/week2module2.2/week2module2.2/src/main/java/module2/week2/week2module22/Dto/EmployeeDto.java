package module2.week2.week2module22.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import module2.week2.week2module22.annotation.EmployeeRoleValidation;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    @NotNull(message = "Required field in employee : name")
    private String name;
    private String email;
    private Integer age;

    @NotNull(message = "Required field in employee : role")
    //@Pattern(regexp = "^(ADMIN|USER)$"  , message = "Role of employee can be user or admin ")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salry of the employee should be not null")
    @Positive(message = "salary of employee should be positive")
    @Digits(integer = 6 , fraction = 2 , message = "The slary can be in form XXXX.XY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;
    @PastOrPresent(message = "Date should not be in future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;


}
