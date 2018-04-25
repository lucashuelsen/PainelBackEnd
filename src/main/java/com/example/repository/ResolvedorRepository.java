package com.example.repository;

import com.example.model.Resolvedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ResolvedorRepository extends CrudRepository<Resolvedor, Long>{
    
}
