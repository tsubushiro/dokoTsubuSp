package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Mutter;

@Repository
public class MuttersRepository {
	
	@Autowired
	MutterCrudRepository repository;
	
	// JDBDtempleteではなくて、CrudRepositoryを使用
	
	public List<Mutter> findAll() {
		List<Mutter> mutterList =(List<Mutter>) repository.findAll();
		mutterList.sort((a,b)->(b.getId()-a.getId()));//crudRepositoryのソート方法が分からなかったのでJavaでソート
		return mutterList;
	}
	
	public boolean create(Mutter mutter){
		Mutter result = repository.save(mutter);
		if(result != null) {
			// System.out.println("入れた！");
			return true;
		}
		return false;
	}
}
