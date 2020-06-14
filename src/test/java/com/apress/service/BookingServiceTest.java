package com.apress.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apress.domain.Booking;
import com.apress.dto.BookingDTO;
import com.apress.repository.BookingRepository;
import com.apress.service.mappers.BookingMapper;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@InjectMocks
	private BookingService service;

	@Mock
	private BookingRepository bookingRepository;
	@Mock
	private BookingMapper bookingMapper;

	@Test
	void shouldReturnAllBookings() {
		BookingDTO bookingDTO = BookingDTO.builder().id(1L).comments("comment").build();
		when(bookingMapper.toBookingDTOs(any())).thenReturn(Arrays.asList(bookingDTO));

		Collection<BookingDTO> bookingDTOs = service.findAll();

		assertThat(bookingDTOs.size()).isEqualTo(1);
	}

	@Test
	void shouldReturnBookingById() {
		BookingDTO bookingDTO = BookingDTO.builder().id(1L).comments("comment").build();
		when(bookingRepository.findById(1L)).thenReturn(Optional.of(new Booking()));
		when(bookingMapper.toBookingDTO(any())).thenReturn(bookingDTO);

		Optional<BookingDTO> returnedBookingDTO = service.findById(1L);

		assertThat(returnedBookingDTO.isPresent()).isTrue();
		assertThat(returnedBookingDTO).isEqualTo(Optional.of(bookingDTO));
	}

	@Test
	void shouldSaveBooking() {
		BookingDTO bookingDTO = BookingDTO.builder().id(1L).comments("comment").build();
		when(bookingMapper.toBookingDTO(any())).thenReturn(bookingDTO);

		BookingDTO returnedBookingDTO = service.save(bookingDTO);

		assertThat(returnedBookingDTO).isNotNull();
		verify(bookingRepository).save(any());
	}

	@Test
	void shouldDeleteBookingById() {
		service.deleteById(1L);

		verify(bookingRepository).deleteById(1L);
	}

}
