package app;

import app.domain.Address;
import app.models.Bid;
import app.domain.Student;
import app.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository repository;
	@Autowired
	private BidRepository repository1;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//todo: add 5 students in the database.


		/*Bid d = new Bid("T","g");
		repository1.save(d);
		Bid d1 = new Bid("D","g");*/
		//repository1.save(d1);
		Student student = new Student(101,"Tolganay","8707-233-22-44","tolganay@gmail.com");
		Address address =new Address("Main Street","Fairfiled","52556");
		student.setAddress(address);
		repository.save(student);
		student = new Student(102,"Marat","9707-233-22-44","marat@Gmail.com");
		address =new Address("5th Street","New York","32556");
		student.setAddress(address);
		repository.save(student);

		student = new Student(103,"John","3707-233-22-44","john@gmail.com");
		address =new Address("10th Court","Dallas","42556");
		student.setAddress(address);
		repository.save(student);

		student = new Student(104,"Justin","4707-233-22-44","justin@gmail.com");
		address =new Address("2 Street","Austin","62556");
		student.setAddress(address);
		repository.save(student);

		student= new Student(105,"Katy","5707-233-22-44","katy@gmail.com");
		address =new Address("Main Street","Austin","62556");
		student.setAddress(address);
		repository.save(student);

		//todo: Get all students
		System.out.println("-----------All students ----------------");
		System.out.println(repository.findAll());
		//todo: Get all students with a certain name
		System.out.println("-----------Get all students with a certain name ----------------");
		System.out.println(repository.findStudentByName("Marat"));

		//todo: Get a student with a certain phoneNumber
		System.out.println("-----------Get a student with a certain phoneNumber ----------------");
		System.out.println(repository.findStudentByPhone("5707-233-22-44"));

		//todo: Get all students from a certain city
		System.out.println("-----------Get all students from a certain city ----------------");
		System.out.println(repository.findStudentByCity("Austin"));


	}

}
