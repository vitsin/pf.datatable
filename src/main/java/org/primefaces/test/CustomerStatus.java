package org.primefaces.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CustomerStatus {
	Available,
	NA,
	Disabled,
	Sleeping;
	
	private static final List<CustomerStatus> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CustomerStatus random()  {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
