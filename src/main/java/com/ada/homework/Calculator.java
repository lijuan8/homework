package com.ada.homework;

import org.apache.log4j.Logger;

public class Calculator {

	private static final Logger logger = Logger.getLogger(Calculator.class);
	
	public static void main(String[] args) {
		
		if (args.length != 1) {
			logger.error("invalid argument. need one input!");
			return;
		}
		logger.info("input is " + args[0]);
		Expression ec = new Expression();
		System.out.println("result is " + ec.execute(args[0]));
		logger.info("result is " + ec.execute(args[0]));		
	}

}
