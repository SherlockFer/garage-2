package com.apress.utils;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.apress.domain.Booking;
import com.apress.domain.Part;
import com.apress.domain.Product;
import com.apress.domain.User;
import com.apress.repository.BookingRepository;
import com.apress.repository.PartRepository;
import com.apress.repository.ProductRepository;
import com.apress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoadDemoData implements ApplicationRunner {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PartRepository partRepository;

	@SuppressWarnings("unused")
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Loading database");

		User user1 = userRepository
				.save(User.builder().role("admin").password("123456").fullName("Administrator").mobile("123456789").email("admin@garage.com").build());
		User user2 = userRepository
				.save(User.builder().password("123456").fullName("Mechanic-1").mobile("123456789").email("mechanic-1@garage.com")
				.role("mechanic").build());
		User user3 = userRepository
				.save(User.builder().password("123456").fullName("Mechanic-2").mobile("123456789").email("mechanic-2@garage.com")
				.role("mechanic").build());
		User user4 = userRepository
				.save(User.builder().password("123456").fullName("Customer-3").mobile("123456789").email("mechanic-3@garage.com")
				.role("Customer").build());
		User user5 = userRepository
				.save(User.builder().password("123456").fullName("Customer-1").mobile("123456789").email("Customer-1@garage.com")
				.role("customer").build());
		User user6 = userRepository
				.save(User.builder().password("123456").fullName("Customer-2").mobile("123456789").email("Customer-2@garage.com")
				.role("customer").build());
		User user7 = userRepository
				.save(User.builder().password("123456").fullName("Customer-3").mobile("123456789").email("Customer-3@garage.com")
				.role("customer").build());

		Product product1 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Annual Service").category("base")
						.price(50).build());
		Product product2 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Major Service").category("base")
						.price(60).build());
		Product product3 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Repair or Fault").category("base")
						.price(70).build());
		Product product4 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Major Repair").category("base")
						.price(80).build());
		Product product5 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Wheel alignment")
						.category("extra").price(90).build());
		Product product6 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Grease and lubricat")
						.category("extra").price(70).build());
		Product product7 = productRepository
				.save(Product.builder().reference(UUID.randomUUID().toString()).name("Suspension").category("extra")
						.price(70).build());

		Part part1 = partRepository
				.save(Part.builder().sku(UUID.randomUUID().toString()).name("Engine motor oil").price(50).build());
		Part part2 = partRepository
				.save(Part.builder().sku(UUID.randomUUID().toString()).name("Filter oil").price(60).build());
		Part part3 = partRepository
				.save(Part.builder().sku(UUID.randomUUID().toString()).name("Filer ai").price(70).build());

		Booking booking1 = bookingRepository.save(
				Booking.builder()
				.date(LocalDate.parse("2020-10-01"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 1")
				.status("booked")
				.vehicleBrand("Ford")
				.vehicleEngine("diesel")
				.vehicleModel("M5")
				.vehicleNumberPlate("AAA-111")
				.vehicleType("Car")
				.part(part1)
				.part(part2)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3).build());
		
		Booking booking2 = bookingRepository.save(
				Booking.builder()
				.date(LocalDate.parse("2020-10-02"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 2")
				.status("booked")
				.vehicleBrand("Ford")
				.vehicleEngine("diesel")
				.vehicleModel("M5")
				.vehicleNumberPlate("BBB-222")
				.vehicleType("car")
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3).build());
		
		Booking booking3 = bookingRepository.save(
				Booking.builder()
				.date(LocalDate.parse("2020-10-03"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 3")
				.status("in_service")
				.vehicleBrand("Ford")
				.vehicleEngine("petrol")
				.vehicleModel("M5")
				.vehicleNumberPlate("CCC-333")
				.vehicleType("small_van")
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3).build());
		
		Booking booking4 = bookingRepository.save(
				Booking.builder()
				.date(LocalDate.parse("2020-10-04"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 4")
				.status("fixed")
				.vehicleBrand("Ford")
				.vehicleEngine("petrol")
				.vehicleModel("M5")
				.vehicleNumberPlate("DDD-444")
				.vehicleType("small_bus")
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3).build());
		
		Booking booking5 = bookingRepository.save(
				Booking.builder()
				.date(LocalDate.parse("2020-10-05"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 5")
				.status("collected")
				.vehicleBrand("Ford")
				.vehicleEngine("hybrid")
				.vehicleModel("M5")
				.vehicleNumberPlate("EEE-666")
				.vehicleType("small_bus")
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3).build());
		
		Booking booking6 = bookingRepository.save(
				Booking.builder()
				.date(LocalDate.parse("2020-10-06"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 6")
				.status("unrepairable")
				.vehicleBrand("Ford")
				.vehicleEngine("electric")
				.vehicleModel("M5")
				.vehicleNumberPlate("FFF-777")
				.vehicleType("car")
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3).build());
		
	}
}