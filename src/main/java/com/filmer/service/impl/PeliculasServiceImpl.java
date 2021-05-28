package com.filmer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer.dao.IPeliculasDAO;
import com.filmer.entities.Pelicula;
import com.filmer.service.IPeliculasService;

@Service
public class PeliculasServiceImpl implements IPeliculasService {
	
	@Autowired
	private IPeliculasDAO peliculasDao;

	@Override
	public void save(Pelicula pelicula) {
		peliculasDao.save(pelicula);
		
		
	}

	@Override
	public List<Pelicula> listadoPeliculas() {
		return peliculasDao.findAll();
	}

}
