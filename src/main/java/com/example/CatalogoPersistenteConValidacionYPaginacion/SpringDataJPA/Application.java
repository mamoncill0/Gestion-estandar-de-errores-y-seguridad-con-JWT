package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out.RoleRepositoryPort;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.entity")
public class Application {

    @Bean
    CommandLineRunner init(RoleRepositoryPort roleRepositoryPort) {
        return args -> {
            if (roleRepositoryPort.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepositoryPort.save(adminRole);
            }
            if (roleRepositoryPort.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepositoryPort.save(userRole);
            }
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
