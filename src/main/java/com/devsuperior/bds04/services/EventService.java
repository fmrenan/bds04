package com.devsuperior.bds04.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repository.EventRepository;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto){
		try {
			Event entity = repository.getOne(id);
			entity.setName(dto.getName() == null ? entity.getName() : dto.getName());
			entity.setCity(new City(dto.getCityId(), null));
			entity.setDate(dto.getDate() == null ? entity.getDate() : dto.getDate());
			entity.setUrl(dto.getUrl() == null ? entity.getUrl() : dto.getUrl());
			
			entity = repository.save(entity);

			return new EventDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
		
	}	
}