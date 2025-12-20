package com.nu.clubs.clubs_bakend;

import com.nu.clubs.clubs_bakend.model.Admin;
import com.nu.clubs.clubs_bakend.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClubsBakendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubsBakendApplication.class, args);
	}

	@Bean
	CommandLineRunner seedAdmin(AdminRepository adminRepository) {
		return args -> {
			if (adminRepository.count() == 0) {
				Admin admin = new Admin("admin@example.com", "password", "Default", "Admin");
				admin.setDepartment("IT");
				admin.setAdminLevel("SUPER");
				admin.setCanManageAdmins(true);
				admin.setCanManageClubs(true);
				adminRepository.save(admin);
			}
		};
	}
}
