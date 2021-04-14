package fi.haagahelia.course.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DateRepository extends CrudRepository<Date, Long> {

    List<Date> findByName(@Param("name") String name);
    
}