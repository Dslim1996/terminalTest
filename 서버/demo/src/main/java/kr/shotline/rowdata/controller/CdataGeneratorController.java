package kr.shotline.rowdata.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CdataGeneratorController {

	@GetMapping("/cdata")
	String makecdata() {
		
		int shot;
		int serialNumber = 0;
		//카운터명 
		String[] counterId = {"LCM2020M10001", "LCM2020M10002", "LCM2020M10003", "LCM2020M10004", "LCM2020M10005", "LCM2020M10006","LCM2020M10007", "LCM2020M10008","LCM2020M10009", "LCM2020M10010", "LCM2020M10011","LCM2020M10012", "LCM2020M10013", "LCM2020M10014", "LCM2020M10015", "LCM2020M10016", "LCM2020M10017", "LCM2020M10018", "LCM2020M10019", "LCM2020M10020"};
		List<String> timeList = new ArrayList<>();
		List<String> firstShotTimeList = new ArrayList<>();
		
		//ct정수 15개 생성//////////////////////////
		//ct소수 15개 생성//////////////////////////
		int[] number = new int[20];		
		int[] decimalPoint = new int[20];
		
		for(int i = 0; i < 20; i++) {
			//6~8
			number[i] = (int)((Math.random() * 3) + 6);
			decimalPoint[i] = (int)((Math.random() * 5) + 0);
			//0~4
		}
//				
//		for(int i = 0; i < 15; i++){
//			System.out.print(number[i] + " ");
//		}
//		System.out.println();
//		
//		for(int i = 0; i < 15; i++){
//			System.out.print(decimalPoint[i] + " ");
//		}
//		System.out.println();
		//////////////////////////////////////////
		
		//출력될 C/T 최대 개수 = 정기통신마다 변경
		int ctMaxCounter;
		//C/T 오차 범위
		int errorRange;
		//CT list
		List<String> ctDatas = new ArrayList<>();
		
		//ct값 저장
		Set<String> ctDataSet = new TreeSet<String>();
		
		
		//final ct/shot row data
		Map<String, Integer> ctAndShotRowDatas = new HashMap<>();
		
		//tempRowDatas
		List<String> tempRowDatas = new ArrayList<>();
		
		double timeGap = 0;
		//시간
		for(int t = 0; t < 15; t++) {
			System.out.println();
			
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String dTime = "20210315040000"; //4시
	        
			
//	        System.out.println ("시작시간 : " + dTime);
	        Date date = null;
			try {
				date = simpleDateFormat.parse(dTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			System.out.println ("시작시간 : " + date);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        
	        double second = 0.000; // 2초
	        
	        for(int a = 0; a < 20; a++) {
	        	firstShotTimeList.add(simpleDateFormat.format(cal.getTime()));
	        }
	        
//	        System.out.println("firstShotTimeList" + firstShotTimeList);
	        
	        //수신주기 세팅
	        if(t >= 0) {
	        	timeGap += 3600.000;
	        	cal.add(Calendar.SECOND, (int) timeGap);
	        }
	        
	        for(int a = 0; a < 20; a++) {
	        	timeList.add(simpleDateFormat.format(cal.getTime()));
	        }
//	        System.out.println(t + " 시작");
			//여기서 시작시간 세팅
			
		//ct생성 카운터 ea
		for(int i =0 ; i < decimalPoint.length; i++) {
			
			//0~4
			ctMaxCounter = (int)((Math.random() * 4));
//			System.out.println("출력될 C/T 최대 가짓수" + ctMaxCounter);
			
			//랜덤 선택 수
			int baseCt = Integer.valueOf(number[i] + "" + decimalPoint[i]);
			double fbaseCt = Math.round((baseCt*0.1) * 100)/100.0;
			
//			System.out.println("baseCt(int) " + baseCt + "::" + "fbaseCt(float) " + fbaseCt);
			
			//shot
//			shot = Math.round(600/ctNum);
			shot = (int) Math.ceil(36000/baseCt);
			
//			System.out.println("shot : " + shot);
			
//			for(int a = 0; a < 10; a++) {
//				//0~2
//				System.out.println("randan test : " + (int)(Math.random() * 3));
//			}
//			for(int a = 0; a < 10; a++) {
//				System.out.println("randan test : " + (int)((Math.random() * 5) + 0));
//			}
			
			
			//shot 당 ct생성
			for(int j = 0; j < shot; j++) {
				//0 ~ ctMaxCounter
				errorRange = (int)((Math.random() * ctMaxCounter));
				
				if((int)(Math.random() * 10) % 2 == 0) {
					ctDatas.add(Integer.toString(baseCt + errorRange));					
				}
				else {
					ctDatas.add(Integer.toString(baseCt - errorRange));
				}
				
//				ctDatas.add(baseCt);
			}
//			System.out.println("ctDatas" + ctDatas);
			
			//shot 당 ct 중복제거
			for(int h = 0; h < ctDatas.size(); h++) {
				ctDataSet.add(ctDatas.get(h));
			}
//			System.out.println("CT set" + ctDataSet);
			
			//중복제거된 ct 항목별 카운트 후 ct shot 맵핑
			int counter;
			for(String ctTemp : ctDataSet) {
				counter = 0;
				for(String ctData : ctDatas) {
					if(ctTemp.equals(ctData)) {
						counter++;
					}
				}
//				shotDatas.add(counter);
//				System.out.println(ctTemp + " " + counter);
				ctAndShotRowDatas.put(ctTemp, counter);
			}
			
			String ctAndShotRowData = "";
			int num = 0;
			Iterator<String> keys = ctAndShotRowDatas.keySet().iterator();
			int totalCountTime = 0;
			
			//ct.shot 전체 시간 계산
			for(String key: ctAndShotRowDatas.keySet()) {
				totalCountTime += Integer.valueOf(key)*ctAndShotRowDatas.get(key);
//				System.out.println("totalCountTime????" + totalCountTime);
			}

//			System.out.println("totalCountTime" + totalCountTime);
			
			int subtractCount = 0;
			if(totalCountTime > 36000) {
				while(totalCountTime > 36000) {
					totalCountTime -= (int)(baseCt) ;
//					System.out.println("ctAndShotRowDatas.get(String.valueOf((int)(baseCt)))" + ctAndShotRowDatas.get(String.valueOf((int)(baseCt))));
					ctAndShotRowDatas.put(String.valueOf((int)(baseCt)), ctAndShotRowDatas.get(String.valueOf((int)(baseCt))) -1);
					subtractCount++;
				}				
//				System.out.println("초과 카운터" + subtractCount );
				shot -= subtractCount;
			}
			
//			System.out.println("totalCountTime" + Math.round((totalCountTime*0.1) * 100)/100.0);
//			System.out.println("totalCountTime" + totalCountTime);
			
			//ct/shot data포멧 정리
//			System.out.println("ctAndShotRowData" + ctAndShotRowDatas);
			while(keys.hasNext() || num < 10) {
				if(keys.hasNext()) {
					String key = keys.next();
					ctAndShotRowData += key + "/" + ctAndShotRowDatas.get(key);					
				}else {
					ctAndShotRowData += "*" + "/" + "*";
				}
				
				if(num != 9) {
					ctAndShotRowData += "/";
				}
				
				num++;
			}
			
			//최종 ct값
			Integer maxShotValue = Collections.max(ctAndShotRowDatas.values());
			String maxCtValue = "0";
			for(Map.Entry<String, Integer> m : ctAndShotRowDatas.entrySet()) {
				if(m.getValue() == maxShotValue) {
					maxCtValue = m.getKey();
				}
			}

//			System.out.println("진짜 shot" + shot);
			
			Date lastShotTime = null;
			
			try {
				lastShotTime = simpleDateFormat.parse(timeList.get(i));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(lastShotTime);
			cal.add(Calendar.SECOND, -3600);
			cal.add(Calendar.SECOND, (int)(totalCountTime*0.1));
			
//			System.out.println("simpleDateFormat.format(cal.getTime())" + simpleDateFormat.format(cal.getTime()) + " " + timeList.get(i));
			
			//serialNumber 증가
			serialNumber += 2;
			
			String smapleCdata = "at=CDATA&id="
					+ "LTM2020KRL001" // 터미널변경
					+ "/"
					+ counterId[i]  //카운터변경 ccm
					+ "&sc="
					+ shot // 변경 총shot
					+ "&time="
					+ simpleDateFormat.format(cal.getTime())// 변경 lastshottime
					+ "/"
					+ timeList.get(i) // 변경 수신시간
					+ "&ci=N/"
					+ maxCtValue // 변경???
					+ "/H&ctt="
					+ ctAndShotRowData // 중요변경
					+ "&rmi=1/0/19700101015959/19700101015959/Y&temp="
					+ "*/*/*/*/*/"    // 변경 온도 c/t와 비슷
					+ "20220310030302" // 변경온도 시각?
					+ "/203&ei=22/"
					+ "N&sn="
					+ serialNumber;  //2 시작 +2
			
			System.out.println("smapleCdata : " + smapleCdata);
			
			cal.clear();
			ctAndShotRowDatas.clear();
//			shotDatas.clear();
			ctDatas.clear();
			ctDataSet.clear();
		}
		System.out.println();
		//다음시간 세팅?
		timeList.clear();
		}
		
//		List<Integer> shotlist = new ArrayList<Integer>();

		
//		for(int i = 0; i < shot; i++) {
//			errorRange = (int)((Math.random() * CTNumber));
//			shotlist.add(decimalPoint + errorRange);
//		}		
//		System.out.println("shotlist" + shotlist);
//		
//		String ct;
//		for(int i = 0; i < shotlist.size(); i++) {
//			ct = Integer.toString(number) + Integer.toString(shotlist.get(i));
//			set.add(ct);
//			set.add(shotlist.get(i));
//		}
		
//		System.out.println("set" + set);
		
		
		return "temp";
	}
}
