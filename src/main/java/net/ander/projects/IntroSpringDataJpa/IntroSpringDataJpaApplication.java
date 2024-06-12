package net.ander.projects.IntroSpringDataJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import net.ander.projects.IntroSpringDataJpa.persistence.entity.Customer;
import net.ander.projects.IntroSpringDataJpa.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}

	@Autowired
	private CustomerCrudRepository customerCrudRepository;

	@Bean
    public CommandLineRunner commandLineRunner() {
		return args -> {
//			Customer ander = new Customer();
//			ander.setName("anderson");
//			ander.setPassword("2533038");
//			customerCrudRepository.save(ander);
//            System.out.println("se guardo la entidad Anderson");

//			System.out.println("\n Imprimiendo todos los clientes");
//			List<Customer> customers = customerCrudRepository.findAll();
//			for (Customer customer : customers) {
//                System.out.println(customer.toString());
//            }

			System.out.println("\n Imprimiendo el cliente con id 1");
			Optional<Customer> custoOpt =customerCrudRepository.findById(1L);
			custoOpt.ifPresent(System.out::println);


			System.out.println("\n eliminando el cliente");
			customerCrudRepository.deleteById(1L);


			System.out.println("\n Imprimiendo todos los clientes de nuevo");
			List<Customer> customers = customerCrudRepository.findAll();
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
        };


	}

}
