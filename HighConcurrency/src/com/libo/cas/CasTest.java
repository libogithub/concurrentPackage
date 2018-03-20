package com.libo.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasTest {

	AtomicInteger i = new AtomicInteger();
	
	private void maim() {
		i.incrementAndGet();

	}

}
