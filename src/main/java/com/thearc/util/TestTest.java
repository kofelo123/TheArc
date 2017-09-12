package com.thearc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
	        String today=sdf.format(new Date());
	        System.out.println(today);
	        


	}

}
