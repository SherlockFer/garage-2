package com.apress.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apress.dto.BookingDTO;
import com.apress.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping()
	public ResponseEntity<Collection<BookingDTO>> findAll() {
		Collection<BookingDTO> bookingDTOs = bookingService.findAll();
		return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDTO> findById(@PathVariable Long id) {
		Optional<BookingDTO> bookingDTO = bookingService.findById(id);
		if (!bookingDTO.isPresent()) {
			return new ResponseEntity<BookingDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bookingDTO.get(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Void> create(@Valid @RequestBody BookingDTO bookingDTO) {
		bookingDTO = bookingService.save(bookingDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(buildLocationUri(bookingDTO.getId()));
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	private URI buildLocationUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody BookingDTO bookingDTO, @PathVariable Long id) {
		if (!bookingService.findById(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		bookingDTO.setId(id);
		bookingService.save(bookingDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!bookingService.findById(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		bookingService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}