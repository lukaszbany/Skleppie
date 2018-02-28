package skleppie.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import skleppie.model.Category;
import skleppie.model.Product;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    private static final double DELTA = 1e-15;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void findById_whenFindById_thenReturnProduct() {
        //given
        Product product = prepareProduct();
        int id = testEntityManager.persistAndGetId(product, Integer.class);

        //when
        Product found = productRepository.findById(id);

        //then
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getCategory().getName(), found.getCategory().getName());
        assertEquals(product.getPrice(), found.getPrice(), DELTA);
    }

    @Test
    public void removeById_whenRemoveById_thenRepositoryIsEmpty() {
        //given
        Product product = prepareProduct();
        int id = testEntityManager.persistAndGetId(product, Integer.class);

        //when
        productRepository.removeById(id);
        Product found = productRepository.findById(id);

        //then
        assertTrue(productRepository.findAll().isEmpty());

    }

    @Test
    public void findByName_whenFindByName_thenReturnProduct() {
        //given
        Product product = prepareProduct();
        testEntityManager.persist(product);

        //when
        Product found = productRepository.findByName(product.getName());

        //then
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getCategory().getName(), found.getCategory().getName());
        assertEquals(product.getPrice(), found.getPrice(), DELTA);
    }

    private Product prepareProduct() {
        Product product = new Product();
        Category category = new Category();
        category.setName("Category");
        product.setName("Product");
        product.setCategory(category);
        product.setPrice(99.99);

        return product;
    }

}