package com.database.websockets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Nickolas Mitchell
 */
@Service
public class BulletinService {

	@Autowired
	private BulletinRepository bulletinRepository;

	public List<Bulletin> getBulletin() {
		return bulletinRepository.findAll();
	}

	public Bulletin addBulletin(Bulletin bulletin) {
		bulletinRepository.save(bulletin);
		return bulletin;
	}

	public Long count() {
		return bulletinRepository.count();
	}

	public void deleteById(Long bulletinId) {
		bulletinRepository.deleteById(bulletinId);
	}
}
