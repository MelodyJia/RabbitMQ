/**
 * 
 */
package com.rabbitmq.service;

import java.util.Date;

public class HelloImpl implements HelloService {
	public String sayHello(String uname) {
		System.out.println("received request");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return  "Hello " + uname + "  now time is :" + new Date();
	}

}
