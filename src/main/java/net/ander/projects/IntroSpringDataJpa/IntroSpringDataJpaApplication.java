package net.ander.projects.IntroSpringDataJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}
	@Bean
	public CommandLineRunner validateDSCommand(DataSource ds) {
		return args -> {
			System.out.println("\n probando conexion y ds\n");

			try (Connection conn = ds.getConnection()) {
				PreparedStatement pstm = conn.prepareStatement("SELECT * FROM characters");
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					String mensaje = rs.getString("value") + " - " + rs.getString("name");
					System.out.println(mensaje);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	@Bean
    public CommandLineRunner validateEntityManager(EntityManagerFactory emf	) {
		return args -> {
			System.out.println("\n probando entityManager y ds\n");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			List<Object[]> characters = em.createNativeQuery("SELECT * FROM characters").getResultList();
			characters.forEach(character ->{
				String mensaje = character[0] + " - " + character[1];
				System.out.println(mensaje);
			});
			em.getTransaction().commit();
		};
	}
}
