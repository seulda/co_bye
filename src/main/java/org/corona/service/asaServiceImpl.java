package org.corona.service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.corona.domain.AgeVO;
import org.corona.domain.AreaVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;

@AllArgsConstructor
@Service
public class asaServiceImpl implements asaService {
	
	@Override
	public String today() {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat fmtTime = new SimpleDateFormat("HHmm");
		Calendar date = Calendar.getInstance();
		String today = fmt.format(date.getTime());
		int time = Integer.parseInt(fmtTime.format(date.getTime()));
		
		if (time < 1200) {
			date.setTime(new Date());
			date.add(Calendar.DATE, -1);
			today = fmt.format(date.getTime());
		}
		
		return today;
	}

	// 지역
	@Override
	public String getAreaApi(String startCreateDt, String endCreateDt) throws Exception {

		StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); // URL
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=c1sNS0F8dzRRFujphkwtO4hhp5OmOL%2FM8ZD31ri59F0wB%2B3CtmKCGRzhXc43qEHoEvIdMERNztk0vvVjdNKOFA%3D%3D"); // Service Key
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); // 공공데이터포털에서 받은 인증키
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지번호
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); // 한 페이지 결과 수
		urlBuilder.append("&" + URLEncoder.encode("startCreateDt", "UTF-8") + "=" + URLEncoder.encode(startCreateDt, "UTF-8")); // 검색할 생성일 범위의 시작
		urlBuilder.append("&" + URLEncoder.encode("endCreateDt", "UTF-8") + "=" + URLEncoder.encode(endCreateDt, "UTF-8")); // 검색할 생성일 범위의 종료
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("AreaApi Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb.toString();
	}
	
	@Override
	public ArrayList<AreaVO> asaArea(String jsonString) {

		ArrayList<AreaVO> list = new ArrayList<AreaVO>();

		JSONObject jObject = new JSONObject(jsonString);

		// (response) 0번째 JSONObject를 가져옵니다.
		JSONObject responseObject = jObject.getJSONObject("response");

		// (response -> header) 1번째 JSONObject를 가져와서 key-value를 읽습니다.
		JSONObject headerObject = responseObject.getJSONObject("header");
		String resultCode = headerObject.getString("resultCode");
		String resultMsg = headerObject.getString("resultMsg");

		JSONObject bodyObject = responseObject.getJSONObject("body");
		JSONObject itemsObject = bodyObject.getJSONObject("items");
		String numOfRows = Integer.toString(bodyObject.getInt("numOfRows"));
		String pageNo = Integer.toString(bodyObject.getInt("pageNo"));
		String totalCount = Integer.toString(bodyObject.getInt("totalCount"));

		// (response -> body -> items -> item(node 2개이상)) 세번째 JSONObject를 가져와서 key-value를 읽습니다.
		JSONArray itemArray = itemsObject.getJSONArray("item");
		for (int i = 0; i < itemArray.length(); i++) {
			
			AreaVO avo = new AreaVO();
			JSONObject iobj = itemArray.getJSONObject(i);
			
			avo.setCreateDt(iobj.getString("createDt"));		// 등록일시
			avo.setDeathCnt(iobj.getInt("deathCnt"));			// 사망자 수
			avo.setIncDec(iobj.getInt("incDec"));				// 전일대비 증감 수
			avo.setGubun(iobj.getString("gubun"));				// 시도명
			avo.setIsolClearCnt(iobj.getInt("isolClearCnt"));	// 격리해제 수
			avo.setDefCnt(iobj.getInt("defCnt"));				// 확진자 수
			avo.setOverFlowCnt(iobj.getInt("overFlowCnt"));		// 해외 유입 수
			avo.setLocalOccCnt(iobj.getInt("localOccCnt"));		// 지역 발생 수
			avo.setIsolIngCnt(iobj.getInt("isolIngCnt"));		// 격리중인 환자 수

			list.add(avo);
		}
		
		return list;
	}
	
	
	// 연령, 성별
	@Override
	public String getAgeApi(String startCreateDt, String endCreateDt) throws Exception {

		StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson"); // URL
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=c1sNS0F8dzRRFujphkwtO4hhp5OmOL%2FM8ZD31ri59F0wB%2B3CtmKCGRzhXc43qEHoEvIdMERNztk0vvVjdNKOFA%3D%3D"); // Service Key
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); // 공공데이터포털에서 받은 인증키
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지번호
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); // 한 페이지 결과 수
		urlBuilder.append("&" + URLEncoder.encode("startCreateDt", "UTF-8") + "=" + URLEncoder.encode(startCreateDt, "UTF-8")); // 검색할 생성일 범위의 시작
		urlBuilder.append("&" + URLEncoder.encode("endCreateDt", "UTF-8") + "=" + URLEncoder.encode(endCreateDt, "UTF-8")); // 검색할 생성일 범위의 종료
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("AgeApi Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb.toString();
	}
	
	@Override
	public ArrayList<AgeVO> asaAge(String jsonString) {

		ArrayList<AgeVO> agelist = new ArrayList<AgeVO>();

		JSONObject jObject = new JSONObject(jsonString);

		// (response) 0번째 JSONObject를 가져옵니다.
		JSONObject responseObject = jObject.getJSONObject("response");

		// (response -> header) 1번째 JSONObject를 가져와서 key-value를 읽습니다.
		JSONObject headerObject = responseObject.getJSONObject("header");
		String resultCode = headerObject.getString("resultCode");
		String resultMsg = headerObject.getString("resultMsg");

		JSONObject bodyObject = responseObject.getJSONObject("body");
		String numOfRows = Integer.toString(bodyObject.getInt("numOfRows"));
		String pageNo = Integer.toString(bodyObject.getInt("pageNo"));
		String totalCount = Integer.toString(bodyObject.getInt("totalCount"));
		
		if (totalCount.equals("0")) {
			agelist = null;
		} else {
			
			JSONObject itemsObject = bodyObject.getJSONObject("items");
			// (response -> body -> items -> item(node 2개이상)) 세번째 JSONObject를 가져와서 key-value를 읽습니다.
			JSONArray itemArray = itemsObject.getJSONArray("item");
			for (int i = 0; i < itemArray.length(); i++) {

				AgeVO avo = new AgeVO();
				JSONObject iobj = itemArray.getJSONObject(i);
				
				avo.setGubun(iobj.getString("gubun"));				// 연령, 성별
				avo.setConfCase(iobj.getInt("confCase"));			// 확진자
				avo.setConfCaseRate(iobj.getInt("confCaseRate"));	// 확진률
				avo.setDeath(iobj.getInt("death"));					// 사망자
				avo.setDeathRate(iobj.getFloat("deathRate"));		// 사망률

				agelist.add(avo);
			}
		}
		
		return agelist;
	}
}