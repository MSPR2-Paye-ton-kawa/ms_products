package com.epsieisi.msprprdoduct.services;

import com.epsieisi.msprprdoduct.dto.ProductDto;
import com.epsieisi.msprprdoduct.exceptions.ResourceNotFoundException;
import com.epsieisi.msprprdoduct.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ProductServiceTest {

    /*

    @Autowired
    ProductService productService;

    @Test
    public void isAvailableTest () throws Exception{
        Long idProduct = 6L;
        Integer askedQuantity = 3;
        Boolean expeceted = true;
        Boolean resp = productService.isAvailable(idProduct,askedQuantity);
        assertEquals(expeceted,resp);
    }

    @Test
    public void isNotAvailableTest () throws Exception{
        Long idProduct = 6L;
        Integer askedQuantity = 30;
        Boolean expeceted = false;
        Boolean resp = productService.isAvailable(idProduct,askedQuantity);
        assertEquals(expeceted,resp);
    }

    */

}
