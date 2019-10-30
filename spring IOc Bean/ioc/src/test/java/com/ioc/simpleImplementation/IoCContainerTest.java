package com.ioc.simpleImplementation;

import com.ioc.simpleImplementation.IoCContainer;
import com.ioc.simpleImplementation.car.Audi;
import com.ioc.simpleImplementation.car.Buick;
import com.ioc.simpleImplementation.humen.Humen;
import com.ioc.simpleImplementation.humen.LiSi;
import com.ioc.simpleImplementation.humen.ZhangSan;
import org.junit.Before;
import org.junit.Test;

public class IoCContainerTest {

    private IoCContainer ioCContainer = new IoCContainer();

    @Before
    public void before() {
        ioCContainer.setBean("audi",Audi.class);
        ioCContainer.setBean("buick", Buick.class);
        ioCContainer.setBean("zhangSan", ZhangSan.class, "audi");
        ioCContainer.setBean("liSi", LiSi.class, "buick");
    }

    @Test
    public void test() {
        Humen zhanSan = (Humen) ioCContainer.getBean("zhangSan");
        zhanSan.goHome();

        Humen liSi = (Humen) ioCContainer.getBean("liSi");
        liSi.goHome();

    }
}