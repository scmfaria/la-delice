package br.com.ladelicedata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import br.com.ladelicedomain.entity.ImageEntity;
import br.com.ladelicedomain.entity.MenuEntity;

public class MenuEntityTest {

    private List<MenuEntity> menuEntities;
    private List<ImageEntity> imageEntities;

    @Before
    public void setUp(){
        menuEntities = new ArrayList<>();
        imageEntities = new ArrayList<>();

        ImageEntity i = new ImageEntity();
        imageEntities.add(i);
        menuEntities.add(new MenuEntity());
        menuEntities.get(0).setImageEntities(imageEntities);
    }

    @Test
    public void testVerifyContainsImagesInArray(){
        int size = menuEntities.get(0).getImageEntities().size();

        Assert.assertTrue(size > 0);
    }


}
