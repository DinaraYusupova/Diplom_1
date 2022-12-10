package praktikum;

import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {

    @Test
    public void getNameCorrectBunNameShouldReturnCorrectName() {
        Bun bun = new Bun("appetizing",0);
        Assert.assertEquals("Выбрана не та булочка","appetizing",bun.getName());
    }
    @Test
    public void getNameNullBunNameShouldReturnNullName() {
        Bun bun = new Bun("",0);
        Assert.assertEquals("Выбрана не та булочка","",bun.getName());
    }
    @Test
    public void getPriceCorrectBunPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("",(float) 498.5);
        Assert.assertEquals("Не совпадает цена булочки",498.5,bun.getPrice(),0);
    }
    @Test
    public void getPriceNullBunPriceShouldReturnNullPrice() {
        Bun bun = new Bun("",0);
        Assert.assertEquals("Не совпадает цена булочки",0.0,bun.getPrice(),0);
    }
}