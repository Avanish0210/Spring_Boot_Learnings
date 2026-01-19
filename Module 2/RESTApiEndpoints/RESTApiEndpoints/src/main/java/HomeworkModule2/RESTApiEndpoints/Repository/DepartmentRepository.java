package HomeworkModule2.RESTApiEndpoints.Repository;

import HomeworkModule2.RESTApiEndpoints.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity , Long> {

}
