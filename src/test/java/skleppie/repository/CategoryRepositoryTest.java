package skleppie.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import skleppie.model.Category;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findById_whenFindById_thenReturnCategory() {
        //given
        Category category = prepareCategory();
        int id = testEntityManager.persistAndGetId(category, Integer.class);

        //when
        Category found = categoryRepository.findById(id);

        //then
        assertEquals(category.getName(), found.getName());
        assertEquals(category.getDescription(), found.getDescription());
    }

    @Test
    public void removeById_whenRemoveById_thenRepositoryIsEmpty() {
        //given
        Category category = prepareCategory();
        int id = testEntityManager.persistAndGetId(category, Integer.class);

        //when
        categoryRepository.removeById(id);

        //then
        assertTrue(categoryRepository.findAll().isEmpty());
    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setName("Główna");
        category.setDescription("Główna kategoria sklepu");

        return category;
    }

}