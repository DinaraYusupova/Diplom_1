package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;

public class BurgerTestWithDataBase {
    Database database;
    Burger burger;
    List<Bun> buns;
    List<Ingredient> ingredients;

    @Before
    public void setData() {
        database = new Database();
        burger = new Burger();
        buns = database.availableBuns();
        ingredients = database.availableIngredients();

    }

    @Test
    public void setBunsCorrectDataShouldReturnCorrectResponse() {
        burger.setBuns(buns.get(0));
        Assert.assertEquals("Выбрана не та булочка", "black bun", burger.bun.getName());
    }
    @Test
    public void addIngredientCorrectDataShouldBeAdded() {
        int sizeBegin = burger.ingredients.size();
        burger.addIngredient(ingredients.get(1));
        Assert.assertEquals("Количество ингридиентов не изменилось",sizeBegin+1,burger.ingredients.size());
        List<String> ingredientsNames = new ArrayList<>();
        for (Ingredient ingredient : burger.ingredients) {
            ingredientsNames.add(ingredient.getName());
        }
        Assert.assertEquals("Ингридиента в списке нет",true,ingredientsNames.contains("sour cream"));
    }
    @Test
    public void removeIngredientCorrectDataShouldBeDeleted() {
        burger.addIngredient(ingredients.get(1));
        int sizeBegin = burger.ingredients.size();
        burger.removeIngredient(0);
        int sizeAfter = burger.ingredients.size();
        Assert.assertEquals("Количество ингридиентов не изменилось",sizeBegin-1,burger.ingredients.size());
    }
    @Test
    public void moveIngredientCorrectDataShouldBeMoved() {
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        String firstIngredient = burger.ingredients.get(0).getName();
        String secondIngredient = burger.ingredients.get(1).getName();
        burger.moveIngredient(0,1);
        Assert.assertEquals("Не верный порядок ингридиентов",secondIngredient,burger.ingredients.get(0).getName());
        Assert.assertEquals("Не верный порядок ингридиентов",firstIngredient,burger.ingredients.get(1).getName());
    }
    @Test
    public void getPriceCorrectDataShouldReturnCorrectPrice() {
        burger.setBuns(buns.get(0));
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        Assert.assertEquals("Не верный порядок ингридиентов", 700, burger.getPrice(), 0);
    }
    @Test
    public void getReceiptCorrectDataShouldReturnCorrectReceipt(){
        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(burger.bun.getName()));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(burger.ingredients.get(0).getName()));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(burger.ingredients.get(1).getName()));
    }
}
