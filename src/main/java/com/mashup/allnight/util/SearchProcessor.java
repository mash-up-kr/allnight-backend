package com.mashup.allnight.util;

import com.mashup.allnight.service.SearchService;

import javax.inject.Inject;

public class SearchProcessor {

    private SearchService searchService;

//    @Inject
    public SearchProcessor(SearchService searchService) {
        this.searchService = searchService;
    }
}
