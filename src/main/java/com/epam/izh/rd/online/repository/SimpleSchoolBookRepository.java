package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.time.LocalDate;
import java.util.*;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks;
    private int i;

    public SimpleSchoolBookRepository() {
        schoolBooks = new SchoolBook[0];
        i = 0;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (book == null) {
            throw new NullPointerException();
        }
        schoolBooks = resize(schoolBooks.length + 1, schoolBooks);
        schoolBooks[i++] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return new SchoolBook[0];
        }
        SchoolBook[] books = new SchoolBook[0];
        int count = 0;

        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                books = resize(books.length + 1, books);
                books[count++] = schoolBooks[i];
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        if (name == null) {
            return false;
        }
        if (isEmpty()) {
            return false;
        }
        boolean removed = false;
        int removeCount = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = null;
                removed = true;
                removeCount++;
                this.i--;
            }
        }
        if (removed) {
            schoolBooks = removeNull(schoolBooks, schoolBooks.length-removeCount);
        }
        return removed;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }

    private SchoolBook[] resize(int capacity, SchoolBook[] arr) {
        return Arrays.copyOf(arr, capacity);
    }

    private SchoolBook[] removeNull(SchoolBook[] array, int capacity) {
        SchoolBook[] temp = new SchoolBook[capacity];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (array[i] != null) {
                temp[j++] = array[i];
            }
        }
        return temp;
    }

    private boolean isEmpty() {
        return schoolBooks.length == 0;
    }

    public static void main(String[] args) {
        SimpleSchoolBookRepository repository = new SimpleSchoolBookRepository();
        repository.save(new SchoolBook(1000, "Odyssey", "Homer", null, LocalDate.of(-800, 2,3)));
        repository.save(new SchoolBook(600, "The Witcher", "Andjey", "Sapkovskie", LocalDate.of(-800, 2,3)));
        repository.save(new SchoolBook(1000, "Harry Potter", "Johan", "Rowling", LocalDate.of(-800, 2,3)));
        repository.save(new SchoolBook(1000, "Math 9's grade", "Johan", "Ister", LocalDate.of(-800, 2,3)));
        repository.save(new SchoolBook(1000, "Physics", "Homer", null, LocalDate.of(-800, 2,3)));

        SchoolBook[] books = repository.findByName("Johan");

        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }
        repository.removeByName("Johan");
        repository.removeByName("Oleksandr");
        repository.save(new SchoolBook(100, "Fairy Tales", "People", null, LocalDate.of(-800, 2,3)));
    }
}
