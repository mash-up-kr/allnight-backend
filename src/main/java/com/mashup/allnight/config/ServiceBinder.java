package com.mashup.allnight.config;

import com.mashup.allnight.service.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


import javax.inject.Singleton;

public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(SearchServiceImpl.class).to(SearchService.class).in(Singleton.class);
        bind(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
    }
}


