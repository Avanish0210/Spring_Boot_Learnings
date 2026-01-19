package com.SpringBootLectures.JPAtuts;

import com.SpringBootLectures.JPAtuts.entities.ProductEntity;
import com.SpringBootLectures.JPAtuts.repositories.ProductRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpAtutorialApplicationTests {

    @Autowired
    ProductRepositories productRepositories;
	@Test
	void contextLoads() {
	}

    @Test
    void testRepository() {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("nestle234")
                .title("Nestle chocolate")
                .price(BigDecimal.valueOf(23.45))
                .quantity(4)
                .build();
        ProductEntity savedProductEntity = productRepositories.save(productEntity);
        System.out.println(savedProductEntity);
    }

    @Test
    void getRepository(){
        //List<ProductEntity> entities = productRepositories.findByCreatedAtAfter(
           //     LocalDateTime.of(2024 , 1,1,0,0,0));
        //List<ProductEntity> entities = productRepositories.findByQuantityAndPrice(4,BigDecimal.valueOf(23.45));
        List<ProductEntity> entities = productRepositories.findByTitleContaining("Choco");
        System.out.println(entities);
    }

    @Test
    void getSingleFromRepository(){
        Optional<ProductEntity> productEntity = productRepositories
                .findByTitleAndPrice("pepsi drink", BigDecimal.valueOf(9.4));
        productEntity.ifPresent(System.out::println);

    }

}
