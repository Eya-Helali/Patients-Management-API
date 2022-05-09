package com.example.tp.jpa;

import com.example.tp.jpa.Patients.Patient;
import com.example.tp.jpa.Patients.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class  Application implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
     patientRepository.save(new Patient(1,"eya", new Date(),2300,false));
     patientRepository.save(new Patient(2,"mouna",new Date() ,2300,false));
     patientRepository.save(new Patient(3,"wala", new Date(),2300,true));
     patientRepository.save(new Patient(4,"feriel", new Date(),2300,false));

		System.out.println("*****************************");

     patientRepository.findAll().forEach(p->{
		 System.out.println(p.toString());
	 });
		System.out.println("*****************************");

		Patient  patient= patientRepository.findById(4L).get();
		System.out.println(patient.getName());
		System.out.println("*************************************");
		Page <Patient> patients= patientRepository.findByNameContains("a",PageRequest.of(0,3));
		patients.getContent().forEach(p->{
			System.out.println(p.toString());
		});

		System.out.println("*************************************");
		List<Patient> patients2= patientRepository.findByNameContainsAndMalade("a",true);
		patients2.forEach(p->{
			System.out.println(p.toString());
		});

		System.out.println("*************************************");
		Page<Patient> pagePatient= patientRepository.findAll(PageRequest.of(1,2));
		pagePatient.getContent()  .forEach(p->{
			System.out.println(p.toString());
		});

	}
}
