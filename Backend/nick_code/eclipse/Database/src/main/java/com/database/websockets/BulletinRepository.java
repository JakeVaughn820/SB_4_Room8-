package com.database.websockets;


//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> 
{

}
