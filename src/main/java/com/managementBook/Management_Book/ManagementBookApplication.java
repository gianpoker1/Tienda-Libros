package com.managementBook.Management_Book;

import com.managementBook.Management_Book.view.BookForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class ManagementBookApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext contextSpring = new SpringApplicationBuilder(ManagementBookApplication.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);
		//Ejecutar el codigo para cargar el formulario
		EventQueue.invokeLater(() ->{
			//obtenemos el objeto form a traves de spring
			BookForm bookForm = contextSpring.getBean(BookForm.class);
			bookForm.setVisible(true);
		});
	}
}
