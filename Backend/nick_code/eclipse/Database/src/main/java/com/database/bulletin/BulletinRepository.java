package com.database.bulletin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the bulletin repository. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long>
{

}
