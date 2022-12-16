package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;
    @Mock
    Ingredient thirdIngredient;

    @Before
    public void setData() {
        burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("Raw");
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(firstIngredient.getName()).thenReturn("cream cloud");
        Mockito.when(secondIngredient.getName()).thenReturn("dinosaur cutlet");
        Mockito.when(thirdIngredient.getPrice()).thenReturn(170F);
        Mockito.when(firstIngredient.getPrice()).thenReturn(70F);
        Mockito.when(secondIngredient.getPrice()).thenReturn(300F);
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsCorrectDataShouldReturnCorrectResponse() {
        burger.setBuns(bun);
        Assert.assertEquals("Выбрана не та булочка","Raw",burger.bun.getName());
    }

    @Test
    public void addIngredientCorrectDataShouldBeAdded() {
        int sizeBegin = burger.ingredients.size();
        burger.addIngredient(firstIngredient);
        Assert.assertEquals("Количество ингридиентов не изменилось",sizeBegin+1,burger.ingredients.size());
        List<String> ingredientsNames = getListOfIngredientsNames(burger);
        Assert.assertTrue("Ингридиента в списке нет", ingredientsNames.contains("cream cloud"));
    }

    @Test
    public void removeIngredientCorrectDataShouldBeDeleted() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        int sizeBegin = burger.ingredients.size();
        burger.removeIngredient(0);
        int sizeAfterDelete = burger.ingredients.size();
        Assert.assertEquals("Количество ингридиентов не изменилось при удалении",sizeBegin-1,sizeAfterDelete);
        List<String> ingredientsNames = getListOfIngredientsNames(burger);
        Assert.assertFalse("Ингридиента в списке нет", ingredientsNames.contains("cream cloud"));
    }
    @Test
    public void moveIngredientCorrectDataShouldBeMoved() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        String firstIngredientName = burger.ingredients.get(0).getName();
        String secondIngredientName = burger.ingredients.get(1).getName();
        burger.moveIngredient(0,1);
        Assert.assertEquals("Не верный порядок ингридиентов",secondIngredientName,burger.ingredients.get(0).getName());
        Assert.assertEquals("Не верный порядок ингридиентов",firstIngredientName,burger.ingredients.get(1).getName());
    }

    @Test
    public void getPriceCorrectDataShouldReturnCorrectPrice() {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        Assert.assertEquals("Не верная цена бургера",740,burger.getPrice(),0);
    }

    @Test
    public void getReceiptCorrectDataShouldReturnCorrectReceipt(){
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        String receipt = "(==== Raw ====)\r\n" +
                "= sauce cream cloud =\r\n" +
                "= filling dinosaur cutlet =\r\n" +
                "(==== Raw ====)\r\n" +
                "\r\nPrice: 570,000000\r\n";
        Assert.assertEquals("Не верный рецепт бургера", receipt,burger.getReceipt());
    }


    //Метод для получения списка названий ингридиентов, содержащихся в бургере
    private List<String> getListOfIngredientsNames(Burger burger){
        List<String> ingredientsNames = new ArrayList<>();
        for (Ingredient ingredient : burger.ingredients) {
            ingredientsNames.add(ingredient.getName());
        }
        return ingredientsNames;
    }
}
