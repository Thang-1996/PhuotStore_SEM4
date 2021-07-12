package com.example.phuotstore.repository;

import com.example.phuotstore.model.Comment;
import com.example.phuotstore.model.Image;
import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Boolean existsByImageName(String imgName);

    @Query("SELECT img FROM Image img WHERE img.imgID = ?1")
    Image findByID(int imgID);

    @Query("SELECT img FROM Image img WHERE img.imgID = ?1")
    Optional<Image> findImageByID(int imgID);

    @Query("SELECT img FROM Image img WHERE img.product.productID =?1")
    Page<Image> findImagesByProductID(Integer productID, Pageable pageable);

    @Query("SELECT img FROM Image img WHERE img.status = 'SHOW' OR img.status = 'HIDDEN'")
    Page<Image> getAllImages(Pageable pageable);

    @Query("SELECT img FROM Image img WHERE img.status = 'SHOW' ")
    Page<Image> findPaginateImagesStatusShow(Pageable pageable);

    @Query("SELECT img FROM Image img WHERE img.status = 'HIDDEN' ")
    Page<Image> findPaginateImagesStatusHidden(Pageable pageable);
}
