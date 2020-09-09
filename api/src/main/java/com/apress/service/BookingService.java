package com.apress.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.defaulter.BookingDefaulter;
import com.apress.domain.Booking;
import com.apress.dto.BookingDTO;
import com.apress.helper.BookingTotal;
import com.apress.mappers.BookingMapper;
import com.apress.repository.BookingRepository;
import com.apress.sender.PlateMessageSender;
import com.apress.validation.BookingValidator;

@Service

public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private BookingMapper bookingMapper;
	@Autowired
	private BookingDefaulter bookingDefaulter;
	@Autowired
	private BookingValidator bookingValidator;
	@Autowired
	private BookingTotal bookingTotal;
	@Autowired
	private PlateMessageSender plateMessageSender;

	public Collection<BookingDTO> findAll() {
		Collection<Booking> bookings = bookingRepository.findAll();
		Collection<BookingDTO> bookingDTOs = bookingMapper.toBookingDTOs(bookings);
		for (BookingDTO bookingDTO : bookingDTOs) {
			bookingDTO.setTotal(bookingTotal.calcTotal(bookingDTO));
		}
		return bookingDTOs;
	}

	public Collection<BookingDTO> findAll(BookingDTO bookingDTO) {
		Booking booking = bookingMapper.toBooking(bookingDTO);
		Example<Booking> example = Example.of(booking);
		Collection<Booking> bookings = bookingRepository.findAll(example);
		return bookingMapper.toBookingDTOs(bookings);
	}

	public Optional<BookingDTO> findById(Long id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if (booking.isPresent()) {
			BookingDTO bookingDTO = bookingMapper.toBookingDTO(booking.get());
			bookingDTO.setTotal(bookingTotal.calcTotal(bookingDTO));
			return Optional.of(bookingDTO);
		}
		return Optional.empty();
	}

	public boolean existsById(Long id) {
		return bookingRepository.existsById(id);
	}

	@Transactional
	public BookingDTO save(BookingDTO bookingDTO) {
		bookingDefaulter.populateDefaults(bookingDTO);
		bookingValidator.validate(bookingDTO);
		if (bookingDTO.hasErrors()) {
			return bookingDTO;
		}
		Booking booking = bookingRepository.save(bookingMapper.toBooking(bookingDTO));
		plateMessageSender.sendVehiclePlate(bookingMapper.toAuditDTO(booking));
		return bookingMapper.toBookingDTO(booking);
	}

	@Transactional
	public void deleteById(Long id) {
		bookingRepository.deleteById(id);
	}

}