package com.mashup.allnight.config;

import com.mashup.allnight.service.*;
//import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.internal.inject.AbstractBinder;


import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Singleton;

public class ServiceBinder extends AbstractBinder {

//    private static WeldContainer container = null;
//    private static Weld weld = null;

    @Override
    protected void configure() {
//        weld = new Weld();
//        container = weld.initialize();
//        BeanManager bm = getBeanManager();
        bind(SearchServiceImpl.class).to(SearchService.class).in(Singleton.class);
//        bind(getBean(bm,SearchServiceImpl.class)).to(SearchService.class);
    }

//    private BeanManager getBeanManager() {
//        // is there a better way to get the bean manager?
//        return container.getBeanManager();
////        return new Weld().get.getBeanManager();
//    }
//
//    private <T> T getBean(BeanManager bm, Class<T> clazz) {
//        Bean<T> bean = (Bean<T>) bm.getBeans(clazz).iterator().next();
//        CreationalContext<T> ctx = bm.createCreationalContext(bean);
//        return (T) bm.getReference(bean, clazz, ctx);
//    }
}


