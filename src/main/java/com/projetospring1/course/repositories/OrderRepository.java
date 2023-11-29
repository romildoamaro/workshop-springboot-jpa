package com.projetospring1.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetospring1.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
