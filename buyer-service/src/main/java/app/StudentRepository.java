package app;

import app.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
    @Query("{'name' : ?0}")
    List<Student> findStudentByName(String name);

    @Query("{'phoneNumber' : ?0}")
    List<Student>  findStudentByPhone(String phone);

    @Query("{'address.city' : ?0}")
    List<Student>  findStudentByCity(String city);

}
