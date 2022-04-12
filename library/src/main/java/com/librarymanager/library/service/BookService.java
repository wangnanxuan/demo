package com.librarymanager.library.service;

import java.util.Map;

public interface BookService {
    public Map<String,Object> bookPage(int page, int size);
}
