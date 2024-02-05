package com.emojin.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
	@Query(value = "select * from gallery where program_id=?",nativeQuery=true)
	List<Gallery> findByProgId(Long progId);
    // You can add custom query methods here if needed
	@Query(value = "select * from gallery where vendor_id=?",nativeQuery=true)
	List<Gallery> findGalleryByVendorId(Long id);
}

