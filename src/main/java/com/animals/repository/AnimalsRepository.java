package com.animals.repository;

import com.animals.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsRepository extends JpaRepository<Animal, Long> {

    //Page<Cliente> findClienteByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
