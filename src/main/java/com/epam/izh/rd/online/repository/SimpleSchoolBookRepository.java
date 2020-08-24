package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.Objects;

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
        resize(schoolBooks.length + 1);
        schoolBooks[i++] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] temp = new SchoolBook[schoolBooks.length];
        int index;
        int counter = 0;
        while (true) {
            index = findIndexByName(name);
            if (index == -1) {
                break;
            }
            temp[counter++] = schoolBooks[index];
        }
        return temp;
    }

    @Override
    public boolean removeByName(String name) {
        int index;
        while (true) {
            index = findIndexByName(name);
            if (index == -1) {
                break;
            }
            schoolBooks[index] = null;
        }
        removeNull(schoolBooks);
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }

    private void resize(int capacity) {
        if (capacity <= schoolBooks.length) {
            throw new IllegalArgumentException();
        }
        schoolBooks = Arrays.copyOf(schoolBooks, capacity);
    }

    private int findIndexByName(String name) {
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getAuthorName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static SchoolBook[] removeNull(SchoolBook[] array) {
        return (SchoolBook[]) Arrays.stream(array).filter(Objects::nonNull).toArray();
    }

    public static void main(String[] args) {

    }
}
