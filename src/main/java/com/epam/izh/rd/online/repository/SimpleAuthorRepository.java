package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors;

    public SimpleAuthorRepository() {
        authors = new Author[0];
    }

    @Override
    public boolean save(Author author) {
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return null;
    }

    @Override
    public boolean remove(Author author) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    private void resize(int capacity) {
        if (capacity <= authors.length) {
            throw new IllegalArgumentException();
        }
        authors = Arrays.copyOf(authors, capacity);
    }
}
