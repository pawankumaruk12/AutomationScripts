package com.org.api.unittest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GenericTest {

	private static int DEFAULT_NEXT_ID = 1;
	
	@Test
	public void getNextIdFromNameTest() {
		GenericService genericService = new GenericService(null, null);
		
		Integer nextId = genericService.getNextIdFromName(null, null);
		Assert.assertEquals(nextId.intValue(), DEFAULT_NEXT_ID);
		
		nextId = genericService.getNextIdFromName("account", "department1");
		Assert.assertEquals(nextId.intValue(), DEFAULT_NEXT_ID);
		
		nextId = genericService.getNextIdFromName("account", "account1");
		Assert.assertEquals(nextId.intValue(), DEFAULT_NEXT_ID + 1);
	}
	
}
