package com.database.lists;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<UserLists, String> 
{
	
	
}
