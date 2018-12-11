package com.thearc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thearc.domain.JsonVO;
import com.thearc.domain.UserVO;

public class ApiExplorer {


	public static void main(String[] args) throws IOException {
		ApiExplorer apiExplorer = new ApiExplorer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		List<JsonVO> json = apiExplorer.forecast(today);
//		System.out.println("jsontest:" + json.get(0));

	}

	public List<JsonVO> forecast(String today) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=oYb8slgEKC4Wvh8ayfkIBQZChHP3a7xnikE%2BHPwl5gVkh7icQR6mnaHksbyFsYaqoNr%2F%2BUHo%2FfLmgnhsqQEVEg%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("oYb8slgEKC4Wvh8ayfkIBQZChHP3a7xnikE%2BHPwl5gVkh7icQR6mnaHksbyFsYaqoNr%2F%2BUHo%2FfLmgnhsqQEVEg%3D%3D", "UTF-8")); /*서비스 인증*/
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*‘15년 12월 1일발표*/
		urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표 * 기술문서 참조*/
		urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("86", "UTF-8")); /*예보지점의 X 좌표값*/
		urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("90", "UTF-8")); /*예보지점의 Y 좌표값*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("45", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값), json*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		JsonVO vo = new JsonVO();
		String line;
		List<JsonVO> jsonList = new ArrayList<JsonVO>();

		//참고로 날씨관련 배열의 한 요소안에 해당시간의 날씨들의 정보가 한줄에 있는게 아니라 각 날씨,온도 등이 매 배열 단위마다 따로 되어있다.
		while ((line = rd.readLine()) != null) {
			String subVal;
//			System.out.println("line=" + line);
			String[] split = line.split("baseDate");
			//9시
//			System.out.println("length:" + split.length);
			JsonParser parser = new JsonParser();

            //Json 오브젝트 하위에 오브젝트가 있는 구조, 최하위의 item이라는 jsonArray 값들이 필요한데, 한번에 접근하는 방법을 모르겠어서 한 단계씩 접근해서 배열가져온것.
            JsonObject jsonObj = (JsonObject) parser.parse(line);
            JsonObject dataObject = (JsonObject) jsonObj.get("response");
            JsonObject dataObject2 = (JsonObject) dataObject.get("body");
            JsonObject dataObject3 = (JsonObject) dataObject2.get("items");

            //필요한 json배열 값.
            JsonArray jsonArray = (JsonArray) dataObject3.get("item");
//            System.out.println("jsonArraySize="+jsonArray.size());

			for (int arrayIndex = 0; arrayIndex < jsonArray.size(); arrayIndex++) {

                //배열의 한 단위씩
                JsonObject jsonObject = (JsonObject)jsonArray.get(arrayIndex);

                String fcstTime = jsonObject.get("fcstTime").toString().replace("\"","");
                String category = jsonObject.get("category").toString().replace("\"","");
                String fcstValue = jsonObject.get("fcstValue").toString().replace("\"","");


                vo.setTime(fcstTime);

				switch (category) {
					case "POP":
//                        System.out.println("Case-pop");
						vo.setPop(fcstValue);
						break;
					case "REH":
//                        System.out.println("Case-REH");
						vo.setReh(fcstValue);
                        break;
					case "SKY":
//                        System.out.println("Case-SKY");
						vo.setSky(fcstValue);
                        break;
					case "T3H":
//                        System.out.println("Case-T3H");
						vo.setT3h(fcstValue);
                        //vo를 List에 add해준다음 다시 동일 객체를 다시 for문 돌리면 다음 List내에 add되는 각 객체가 동일한 객체라 값이 같아져버림 -> add이후 새로운 객체 생성시켜줌
						jsonList.add(vo);
						vo = new JsonVO();
                        break;
				}
                /*System.out.println("Test-사이클="+arrayIndex);
                System.out.println("test-fcstTime="+fcstTime);
                System.out.println("test-category="+category);
                System.out.println("test-fcstValue="+fcstValue );
                System.out.println("vo="+vo);
                System.out.println("jsonList="+jsonList);*/

			}//for


		}
		rd.close();
		conn.disconnect();
//        System.out.println("test-jsonList:"+jsonList);

		return jsonList;
	}
}
/*
	 public List<JsonVO> forecast(String today) throws IOException{
		 StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); */
/*URL*//*

		 urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=oYb8slgEKC4Wvh8ayfkIBQZChHP3a7xnikE%2BHPwl5gVkh7icQR6mnaHksbyFsYaqoNr%2F%2BUHo%2FfLmgnhsqQEVEg%3D%3D"); */
/*Service Key*//*

		 urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("oYb8slgEKC4Wvh8ayfkIBQZChHP3a7xnikE%2BHPwl5gVkh7icQR6mnaHksbyFsYaqoNr%2F%2BUHo%2FfLmgnhsqQEVEg%3D%3D", "UTF-8")); */
/*서비스 인증*//*

		 urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); */
/*‘15년 12월 1일발표*//*

		 urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); */
/*05시 발표 * 기술문서 참조*//*

		 urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("86", "UTF-8")); */
/*예보지점의 X 좌표값*//*

		 urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("90", "UTF-8")); */
/*예보지점의 Y 좌표값*//*

		 urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("45", "UTF-8")); */
/*한 페이지 결과 수*//*

		 urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); */
/*페이지 번호*//*

		 urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); */
/*xml(기본값), json*//*

		 URL url = new URL(urlBuilder.toString());
		 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 conn.setRequestMethod("GET");
		 conn.setRequestProperty("Content-type", "application/json");
		 System.out.println("Response code: " + conn.getResponseCode());
		 BufferedReader rd;
		 if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			 rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		 } else {
			 rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		 }
		 StringBuilder sb = new StringBuilder();
		 JsonVO vo = new JsonVO();
		 String line;
		 List<JsonVO> jsonList = new ArrayList<JsonVO>();
		 while ((line = rd.readLine()) != null) {
			 String subVal;
             System.out.println("line="+line);
			 String[] split = line.split("fcstValue");
			 //9시
			System.out.println("test:"+split.length);
			 vo.setTime("0900");
			 if(split.length==1){
				 vo.setPop("?");
				 vo.setReh("?");
				 vo.setSky("?");
				 vo.setT3h("?");
			 }else{
                 System.out.println("test-split:"+split[1]);
			 subVal=(split[1].substring(2,4)).replace(",","");
			 vo.setPop(subVal);

			 subVal=(split[3].substring(2,4)).replace(",","");
			 vo.setReh(subVal);

			 subVal=(split[4].substring(2,4)).replace(",","");
			 vo.setSky(subVal);

			 subVal=(split[5].substring(2,4)).replace(",","");
			 vo.setT3h(subVal);
			 }
			 jsonList.add(vo);
			 //
			 //12시
			 vo= new JsonVO();
			 vo.setTime("1200");
			 if(split.length==1){
				 vo.setPop("?");
				 vo.setReh("?");
				 vo.setSky("?");
				 vo.setT3h("?");
			 }else{
			 subVal=(split[10].substring(2,4)).replace(",","");
			 vo.setPop(subVal);

			 subVal=(split[13].substring(2,4)).replace(",","");
			 vo.setReh(subVal);

			 subVal=(split[15].substring(2,4)).replace(",","");
			 vo.setSky(subVal);

			 subVal=(split[16].substring(2,4)).replace(",","");
			 vo.setT3h(subVal);
			 }
			 jsonList.add(vo);
			 //
			 //15시
			 vo= new JsonVO();
			 vo.setTime("1500");
			 if(split.length==1){
				 vo.setPop("?");
				 vo.setReh("?");
				 vo.setSky("?");
				 vo.setT3h("?");
			 }else{
			 subVal=(split[21].substring(2,4)).replace(",","");
			 vo.setPop(subVal);

			 subVal=(split[23].substring(2,4)).replace(",","");
			 vo.setReh(subVal);

			 subVal=(split[24].substring(2,4)).replace(",","");
			 vo.setSky(subVal);

			 subVal=(split[25].substring(2,4)).replace(",","");
			 vo.setT3h(subVal);
			 }
			 jsonList.add(vo);
			 //
			 //18시
			 vo= new JsonVO();
			 vo.setTime("1800");
			 if(split.length==1){
				 vo.setPop("?");
				 vo.setReh("?");
				 vo.setSky("?");
				 vo.setT3h("?");
			 }else{
			 subVal=(split[31].substring(2,4)).replace(",","");
			 vo.setPop(subVal);

			 subVal=(split[34].substring(2,4)).replace(",","");
			 vo.setReh(subVal);

			 subVal=(split[36].substring(2,4)).replace(",","");
			 vo.setSky(subVal);

			 subVal=(split[37].substring(2,4)).replace(",","");
			 vo.setT3h(subVal);
			 }
			 jsonList.add(vo);
			 //
			 System.out.println(jsonList);

			 sb.append(line);

		 }
		 rd.close();
		 conn.disconnect();
		 System.out.println(sb.toString());

		 return jsonList;


	 }
*/




/*     JSONArray json = new JSONArray();
	        JSONArray jsonArray = sb.getJSONArray("header");
 */
/*JSONParser parser = new JSONParser();
	        	Object obj = parser.parse(line);
	        	System.out.println("objValueTest:"+obj);*/
//	        	JSONObject jobj = 
//	        	JsonArray = (JSONArray) obj;
//	        	System.out.println("jsonArrayValueTest"+jsonArray);
//	        	System.out.println("jsongetTest:"+(String)jsonArray.get("resultCode"));
//	        	String jsontest = (String)jsonArray.get("baseTime");
//	        	System.out.println(jsontest);