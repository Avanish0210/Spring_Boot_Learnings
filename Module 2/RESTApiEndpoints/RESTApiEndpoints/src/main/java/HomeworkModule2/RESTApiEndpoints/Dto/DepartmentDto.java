package HomeworkModule2.RESTApiEndpoints.Dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long Id;
    @NotNull
    private String DepartmentName;

    private boolean isActive;
    @PastOrPresent(message = "Date should be in past or present")
    private LocalDate CreatedAt;
}
