package br.com.ladelicedata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import br.com.ladelicedomain.entity.MenuEntity;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class CallServiceTest {
    
    private List<MenuEntity> menuEntities;

    @Before
    public void setUp(){
        menuEntities = new ArrayList<>();
        
        menuEntities.add(new MenuEntity());
        menuEntities.add(new MenuEntity());
        menuEntities.add(new MenuEntity());
        menuEntities.add(new MenuEntity());
    }

    public Observable<List<MenuEntity>> getMenu() {
        return Observable.just(menuEntities);
    }

    @Test
    public void testContains4Menus() {
        TestObserver<List<MenuEntity>> testObserver = new TestObserver<>();
        getMenu().subscribe(testObserver);

        List<MenuEntity> res = testObserver.values().get(0);
        Assert.assertTrue(res.size() == 4);
    }
}
