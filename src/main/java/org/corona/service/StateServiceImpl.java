package org.corona.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.corona.domain.AreaVO;
import org.corona.domain.DisasterVO;
import org.corona.domain.StateVO;
import org.corona.domain.ncovVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {

	@Override
	public String today() {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat fmtTime = new SimpleDateFormat("HHmm");
		Calendar date = Calendar.getInstance();
		String today = fmt.format(date.getTime());
		int time = Integer.parseInt(fmtTime.format(date.getTime()));
		
		if (time < 1000) {
			date.setTime(new Date());
			date.add(Calendar.DATE, -1);
			today = fmt.format(date.getTime());
		}
		
		return today;
	}
	
	@Override
	public String day(String sDay) {
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        cal.add(Calendar.DATE, -10);
        String day = fmt.format(cal.getTime());
        
		return day;
	}
	
	@Override
	public String yday(String sDay) {
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        cal.add(Calendar.DATE, -1);
        String yday = fmt.format(cal.getTime());
        
		return yday;
	}
	
	@Override
	public String getCovidStateApi(String sDay, String eDay) throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");	// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=A04I%2FDZd%2FTh0VEDOtCHizLhisxQeu9JTtTshqLMp9wJYwCF0wAAbOC5MpN%2BGwGuessW1Z%2FqgzaVdEgJCuPqodw%3D%3D"); // Service Key
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8"));						// 공공데이터포털에서 받은 인증키
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));							// 페이지번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));						// 한 페이지 결과 수
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(sDay, "UTF-8"));					// 검색할 생성일 범위의 시작
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(eDay, "UTF-8"));						// 검색할 생성일 범위의 종료
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));							// json type return
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("CovidStateApi Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        //System.out.println("sb.toString(): "+sb.toString());
        
        JSONObject jObject = new JSONObject(sb.toString());
		JSONObject responseObject = jObject.getJSONObject("response");
		JSONObject headerObject = responseObject.getJSONObject("header");
	    int resultCode = headerObject.getInt("resultCode");
	    
	    if (resultCode == 99) {
	    	return "e";
	    }
        
		return sb.toString();
	}

	@Override
	public ArrayList<StateVO> covidState(String result) {
		
		ArrayList<StateVO> list = new ArrayList<StateVO>();
		
		JSONObject jObject = new JSONObject(result);
		JSONObject responseObject = jObject.getJSONObject("response");
		// response-header-> resultCode,resultMsg
		JSONObject headerObject = responseObject.getJSONObject("header");
	    String resultCode = headerObject.getString("resultCode");
	    String resultMsg = headerObject.getString("resultMsg");
		// response-body-> items, numOfRows, pageNo, totalCount
	    JSONObject bodyObject = responseObject.getJSONObject("body");
	    String numOfRows = Integer.toString(bodyObject.getInt("numOfRows"));
	    String pageNo = Integer.toString(bodyObject.getInt("pageNo"));
	    String totalCount = Integer.toString(bodyObject.getInt("totalCount"));
		// items-item
	    JSONObject itemsObject = bodyObject.getJSONObject("items");
	    JSONArray itemArray = itemsObject.getJSONArray("item");
	    
	    for (int i = 0; i < itemArray.length(); i++) {
	    	StateVO svo = new StateVO();
	    	JSONObject iobj = itemArray.getJSONObject(i);
	    	
	        svo.setSeq(iobj.getInt("seq"));								// 게시글번호
	        svo.setStateDt(String.valueOf(iobj.getInt("stateDt")));		// 기준일
	    	svo.setStateTime(iobj.getString("stateTime"));				// 기준시간
	        svo.setDecideCnt(iobj.getInt("decideCnt"));					// 확진자수
	        svo.setADecideCnt(iobj.getInt("decideCnt"));
	        svo.setClearCnt(iobj.getInt("clearCnt"));					// 격리해제수
	        svo.setExamCnt(iobj.getInt("examCnt"));						// 검사진행수
	        svo.setDeathCnt(iobj.getInt("deathCnt"));					// 사망자수
	        svo.setADeathCnt(iobj.getInt("deathCnt"));
	        svo.setCareCnt(iobj.getInt("careCnt"));						// 치료중환자수
	        svo.setACareCnt(iobj.getInt("careCnt"));
	        svo.setResutlNegCnt(iobj.getInt("resutlNegCnt"));			// 결과음성수
	        svo.setAccExamCnt(iobj.getInt("accExamCnt"));				// 누적검사수
	        svo.setAccExamCompCnt(iobj.getInt("accExamCompCnt"));		// 누적검사완료수
	        svo.setAccDefRate(iobj.getFloat("accDefRate"));				// 누적확진률
	        svo.setCreateDt(iobj.getString("createDt"));				// 등록시간
	        //svo.setUpdateDt(iobj.getString("updateDt"));				// 수정시간
	        
	        //System.out.println((i+1) + "번째 item: " + svo);
	        list.add(svo);
	    }
		return list;
	}
	
	@Override
	public ArrayList<StateVO> aCovidState(ArrayList<StateVO> list) throws ParseException {
		
		List<StateVO> dayList = new ArrayList<StateVO>();
		dayList = list.subList(1, list.size());
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		String dt = "";
		
		for(int i = 0; i < list.size()-1; i++) {
			list.get(i).setADecideCnt(list.get(i).getDecideCnt() - dayList.get(i).getDecideCnt());
			list.get(i).setACareCnt(list.get(i).getCareCnt() - dayList.get(i).getCareCnt());
			list.get(i).setADeathCnt(list.get(i).getDeathCnt() - dayList.get(i).getDeathCnt());
			
			date = fmt.parse(list.get(i).getStateDt());
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			dt = fmt.format(cal.getTime());
			list.get(i).setStateDt(dt);
		}
		list.remove(list.size()-1);
		
		return list;
	}

	
	@Override
	public String Crawler() {
		//String URL = "https://m.news.naver.com/covid19/live.nhn";
		//String URL = "https://corona-live.com/";
		String URL = "http://ncov.mohw.go.kr/bdBoardList_Real.do";
		//ncovVO result = new ncovVO();
		String result = "";
		
		try {
            // Connection 생성
            Connection conn = Jsoup.connect(URL);
            // HTML 파싱
            Document doc = conn.get(); // conn.post();
            //System.out.println(html.toString()); 
            
    		String toC = "";
            Elements inner_value = doc.getElementsByClass("inner_value");
            for( Element elm : inner_value ) {
            	toC += elm;
            }
            toC = toC.replace("<p class=\"inner_value\">", "");
            toC = toC.replace(" ", "");
            toC = toC.replace(",", "");
    		String[] cut = toC.split("</p>");

    		String toD = "";
    		Elements t_date = doc.getElementsByClass("t_date");
            for( Element elm : t_date ) {
            	toD += elm;
            }
            //System.out.println("toD String : "+toD);
            
            String toAC = "";
    		Elements ca_value = doc.getElementsByClass("ca_value");
            for( Element elm : ca_value ) {
            	toAC += elm;
            }
            //System.out.println("toAC String : "+toAC);
    		
    		//result.setAddCnt(cut[0]);
    		result = cut[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return result;
	}

	@Override
	public String getDisasterMsgApi() throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1741000/DisasterMsg3/getDisasterMsg1List");	// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=A04I%2FDZd%2FTh0VEDOtCHizLhisxQeu9JTtTshqLMp9wJYwCF0wAAbOC5MpN%2BGwGuessW1Z%2FqgzaVdEgJCuPqodw%3D%3D"); // Service Key
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("인증키", "UTF-8"));		// 공공데이터포털에서 받은 인증키
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));				// 페이지번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("150", "UTF-8"));			// 한 페이지 결과 수
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));				// 호출문서 형식
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("DisasterMsgApi Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
	public ArrayList<DisasterVO> DisasterMsg(String result) {
		
		ArrayList<DisasterVO> list = new ArrayList<DisasterVO>();
		int count = 0;
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		String today = fmt.format(Calendar.getInstance().getTime());
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat yfmt = new SimpleDateFormat("dd");
        String yday = yfmt.format(cal.getTime());
		
		JSONObject jObject = new JSONObject(result);
		JSONArray DisasterArray = jObject.getJSONArray("DisasterMsg");
		// response - head -> list_total_count, RESULT		// RESULT -> CODE, MESSAGE
		// response - row -> create_date, location_id, location_name, md101_sn, msg, send_platform
		JSONObject rowObject = DisasterArray.getJSONObject(1);
		JSONArray rowArray = rowObject.getJSONArray("row");
	    
	    for (int i = 0; i < rowArray.length(); i++) {
	    	DisasterVO dvo = new DisasterVO();
	    	JSONObject iobj = rowArray.getJSONObject(i);
	    	
	    	if( iobj.getString("create_date").substring(0,10).equals(today) == false ) { continue; }
	    	if( iobj.getString("msg").contains("확진")==false || iobj.getString("msg").contains("명")==false ) { continue; }
	    	if( iobj.getString("msg").contains(" 0시")==true || iobj.getString("msg").contains("00시")==true
	    			|| iobj.getString("msg").contains("24시")==true || iobj.getString("msg").contains(yday)==true
	    			|| iobj.getString("location_name").contains("전체")==true ) { continue; }
	    	
	    	dvo.setCreate_date(iobj.getString("create_date"));		// 생성일시
	    	dvo.setLocation_id(iobj.getString("location_id"));		// 수신지역 코드
	    	dvo.setLocation_name(iobj.getString("location_name"));	// 수신지역 이름
	    	dvo.setMd101_sn(iobj.getString("md101_sn"));			// 일련번호
	    	dvo.setMsg(iobj.getString("msg"));						// 내용
	    	dvo.setSend_platform(iobj.getString("send_platform"));	// 발신처
	        
	        //System.out.println("(" + i + ") 번째 Disaster row: " + dvo);
	        list.add(dvo);
	        count++;
	    }
	    System.out.println("DisasterMsg count: " + count);
		return list;
	}

	@Override
	public int msgCount(ArrayList<DisasterVO> list) {
		
		int mCnt = 0;
		String[] keyword = { "^.*(주민.)(\\d{1,3})(명).*$","^.*(확진자)(\\d{1,3})(명.발생).*$","^.*(확진자)(\\d{1,3})(명).*$",
							 "^.*(주민.)(\\d{1,3})(명.추가확진).*$","^.*(일일확진자.)(\\d{1,3})(명.발생).*$","^.*(확진자.)(\\d{1,3})(명).*$",
							 "^.*(확진자.)(\\d{1,3})(명.발생).*$","^.*(확진자.발생.)(\\d{1,3})(명).*$","^.*(코로나19확진자.)(\\d{1,3})(명.발생).*$",
							 "^.*(코로나19.확진자.)(\\d{1,3})(명.발생).*$","^.*(확진자.)(\\d{1,3})(명발생).*$","^.*(신규.확진자.)(\\d{1,3})(명.발생).*$",
							 "^.*(코로나19.신규확진자.)(\\d{1,3})(명.발생).*$","^.*(확진.)(\\d{1,3})(명).*$","^.*(신규확진자.)(\\d{1,3})(명.발생).*$" };
        for(int i = 0; i < list.size(); i++){
        	for(int j = 0; j < keyword.length; j++) {
        		Pattern pattern = Pattern.compile(keyword[j]);
                Matcher matcher = pattern.matcher(list.get(i).getMsg());
                if (matcher.find()){
                	mCnt += Integer.parseInt(matcher.group(2));
                	//System.out.println("@@ find @@ >>>>>> " + matcher.group(2));
                	break;
                }
        	}
        }
		System.out.println("!!!!!!! msg count : " + mCnt);
		return mCnt;
	}
	
	
	
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
	
}
