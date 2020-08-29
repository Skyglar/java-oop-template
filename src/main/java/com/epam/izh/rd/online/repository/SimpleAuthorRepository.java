package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.time.LocalDate;
import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors;
    private int index;

    public SimpleAuthorRepository() {
        authors = new Author[0];
        index = 0;
    }

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        resize(authors.length+1);
        authors[index++] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author a: authors) {
            if (a.getName().equals(name) && a.getLastName().equals(lastname)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                authors[i] = null;
                index--;
                break;
            }
        }
        authors = removeNull(authors.length - 1);
        return true;
    }

    private Author[] removeNull(int capacity) {
        Author[] temp = new Author[capacity];
        for (int i = 0, j = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                temp[j++] = authors[i];
            }
        }
        return temp;
    }

    @Override
    public int count() {
        return authors.length;
    }

    private void resize(int capacity) {
        if (capacity <= authors.length) {
            throw new IllegalArgumentException();
        }
        authors = Arrays.copyOf(authors, capacity);
    }

    public static void main(String[] args) {
        SimpleAuthorRepository repository = new SimpleAuthorRepository();
        Author author = new Author("Anjey", "Sapkowskiy", LocalDate.of(1967, 10, 9), "Poland");
        Author author1 = new Author("Roger", "Moor", LocalDate.of(1967, 10, 9), "USA");
        repository.save(new Author("Johan", "Rowling", LocalDate.of(1967, 10, 9), "Great Britain"));
        repository.save(author);
        repository.save(author1);
        repository.save(new Author("Jason", "Statem", LocalDate.of(1967, 10, 9), "USA"));

        System.out.println(repository.findByFullName("Roger", "Moor"));

        repository.remove(author);
        repository.remove(author1);
        repository.save(new Author("some", "author", LocalDate.of(1111,1,1), "unknown"));
    }
}
