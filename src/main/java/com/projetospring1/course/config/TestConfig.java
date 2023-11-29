package com.projetospring1.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetospring1.course.entities.Category;
import com.projetospring1.course.entities.Order;
import com.projetospring1.course.entities.OrderItem;
import com.projetospring1.course.entities.Payment;
import com.projetospring1.course.entities.Product;
import com.projetospring1.course.entities.User;
import com.projetospring1.course.entities.enums.OrderStatus;
import com.projetospring1.course.repositories.CategoryRepository;
import com.projetospring1.course.repositories.OrderItemRepository;
import com.projetospring1.course.repositories.OrderRepository;
import com.projetospring1.course.repositories.ProductRepository;
import com.projetospring1.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Beauty");
		Category cat2 = new Category(null, "Tecnology");
		
		Product p1 = new Product(null, "The lord of the ring", "lorem", 90.00, "");
		Product p2 = new Product(null, "Elden ring", "lorem", 100.00, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2));
		
		p1.getCategories().add(cat1);
		p2.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2));
		
		User u1 = new User(null, "Test1", "email@test", "652123", "1566645");
		User u2 = new User(null, "Test2", "email@test2", "65212364", "15456645");
		
		Order o1 = new Order(null, Instant.parse("2023-11-13T22:10:15Z"),
				OrderStatus.SHIPPED, u1);
		Order o2 = new Order(null, Instant.parse("2023-11-14T22:30:15Z"),
				OrderStatus.PAID, u2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2));
		
		OrderItem orderI1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem orderI2 = new OrderItem(o1, p2, 3, p2.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(orderI1, orderI2));
		
		Payment pay1 = new Payment(null, Instant.parse("2023-11-14T22:30:15Z"), o2);
		o2.setPayment(pay1);
		
		orderRepository.save(o2);
	}
}
