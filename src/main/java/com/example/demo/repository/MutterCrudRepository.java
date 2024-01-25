package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Mutter;

//public interface MutterCrudRepository extends JpaRepository<Mutter,Integer> {
//	public List<Mutter> findAll();
//}
// CrudRepositoryで分からなかったので

public interface MutterCrudRepository extends CrudRepository<Mutter,Integer> {
//	public List<Mutter> findAll();
}
