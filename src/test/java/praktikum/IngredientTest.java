package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientTest {
    Ingredient ingredient;

    @Before
    public void setData() {
        ingredient = new Ingredient(IngredientType.SAUCE,"black souse", 200);
    }

    @Test
    public void getPriceCorrectDataShouldReturnCorrectPrice() {
        Assert.assertEquals("Неверная цена соуса",200,ingredient.getPrice(),0);
    }
    @Test
    public void getNameCorrectBunNameShouldReturnCorrectName() {
        Assert.assertEquals("Неверное название соуса","black souse",ingredient.getName());
    }
    @Test
    public void testGetTypeCorrectDataShouldReturnCorrectType() {
        Assert.assertEquals("Неверный тип ингридиента",IngredientType.SAUCE,ingredient.getType());
    }
}