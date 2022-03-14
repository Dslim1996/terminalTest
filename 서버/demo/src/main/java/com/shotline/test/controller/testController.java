package com.shotline.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

	@GetMapping("/test")
	String test() {
		int rpm = 50;
		
		//RPM 시간당 shot
//		int shot = 640;
		int shot = 64;
		
		double CT = 9.5;
		
		int decimalPoint = (int)((Math.random() * 9));
		int CTNumber = (int)((Math.random() * 4)+1);
		
		System.out.println("소수점" + decimalPoint);
		System.out.println("소수점" + CTNumber);
		
		int errorRange;
		
		List<Integer> shotlist = new ArrayList<Integer>();
		Set<Integer> set = new TreeSet<Integer>();
		
		for(int i = 0; i < shot; i++) {
			errorRange = (int)((Math.random() * CTNumber));
			shotlist.add(decimalPoint + errorRange);
		}		
		System.out.println("shotlist" + shotlist);
		
		for(int i = 0; i < shotlist.size(); i++) {
			set.add(shotlist.get(i));
		}
		
		System.out.println("set" + set);
		
		String test = "at=CDATA&id="
				+ "LTM2020KRL001" // 변경
				+ "/"
				+ "LCM2020M10004"  //변경
				+ "&sc="
				+ "40" // 변경
				+ "&time="
				+ "20220310025856" // 변경
				+ "/"
				+ "20220310030302" // 변경
				+ "&ci=N/"
				+ "63" // 변경???
				+ "/H&ctt="
				+ "62/2/63/23/64/14/189/1/*/*/*/*/*/*/*/*/*/*/*/*" // 중요변경
				+ "&rmi=1/0/19700101015959/19700101015959/Y&temp="
				+ "*/*/*/*/*/"    // 변경 온도
				+ "20220310030302" // 변경온도 시각?
				+ "/203&ei=22/N&sn=00524";
		return "test";
	}
}
