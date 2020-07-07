package com.redis.test.designMode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2020-04-21 22:42
 **/
public abstract class Rate {
	protected List<Company> observers = new ArrayList<>();

	void addCompany(Company company){
		observers.add(company);
	};

	void removeCompany(Company company){
		observers.remove(company);
	}

	public abstract void change(int number);
}
