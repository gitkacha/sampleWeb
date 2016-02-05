package com.sample.cucumber.book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Library {
	private final List<Book> store = new ArrayList<>();

	public void addBook(final Book book) {
		store.add(book);
	}

	public List<Book> findBooks(final Date from, final Date to) {
		Calendar end = Calendar.getInstance();
		end.setTime(to);
		end.roll(Calendar.YEAR, 1);

		List<Book> result = new ArrayList<>();
		for (Book book : store) {
			if (from.before(book.getPublished()) && end.getTime().after(book.getPublished())) {
				result.add(book);
			}
		}

		Collections.sort(result, new Comparator<Book>() {
			@Override
			public int compare(final Book o1, final Book o2) {
				return o2.getPublished().compareTo(o1.getPublished());
			}
		});

		return result;
	}
}