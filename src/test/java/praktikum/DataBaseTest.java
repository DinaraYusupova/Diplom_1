package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DataBaseTest {
    Database database;
    Burger burger;

    @Before
    public void setData() {
        database = new Database();
        burger = new Burger();
    }
    @Test
    public void availableBunsShouldReturnNotNullList() {
        List<Bun> buns = database.availableBuns();
        MatcherAssert.assertThat(buns.size(), notNullValue());
    }
    @Test
    public void availableIngredientsShouldReturnNotNullList() {
        List<Ingredient> ingredients = database.availableIngredients();
        MatcherAssert.assertThat(ingredients.size(), notNullValue());
    }

}
