package com.cafe24.kangk0269.api;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

public class RadiationApi {
	
	public void weatherApiAction() throws IOException, ParseException, ClassNotFoundException, SQLException {
		//지역번호 배열
    	String[] areaArray = {"90","93","95","98","99","100","101","102","104","105","106","108","112","114","115","119","121","127","129","130","131","133","135","136","137","138","140","143","146","152","155","156","159","162","165","168","169","170","172","174","177","184","185","188","189","192","201","202","203","211","212","216","217","221","226","232","235","236","238","239","243","244","245","247","248"};
    	//어제날짜
		Date dDate = new Date();
		dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
		SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String yesterday = dSdf.format(dDate);
		System.out.println(yesterday);
    	for(int i=0; i<areaArray.length; i++) {
	    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/AsosHourlyInfoService/getWthrDataList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=08uz9XfOZDajbruZ7njxDj6I8k7Uq2%2FVWREr6wFn6Gl9IaWZMyC%2B%2BZG%2BGcISZW5761IA46UEeFma8Wav2ot8Ww%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("24", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)*/
	        urlBuilder.append("&" + URLEncoder.encode("dataCd","UTF-8") + "=" + URLEncoder.encode("ASOS", "UTF-8")); /*자료 분류 코드*/
	        urlBuilder.append("&" + URLEncoder.encode("dateCd","UTF-8") + "=" + URLEncoder.encode("HR", "UTF-8")); /*날짜 분류 코드*/
	        urlBuilder.append("&" + URLEncoder.encode("startDt","UTF-8") + "=" + URLEncoder.encode(yesterday, "UTF-8")); /*조회 기간 시작일*/
	        urlBuilder.append("&" + URLEncoder.encode("startHh","UTF-8") + "=" + URLEncoder.encode("00", "UTF-8")); /*조회 기간 시작시*/
	        urlBuilder.append("&" + URLEncoder.encode("endDt","UTF-8") + "=" + URLEncoder.encode(yesterday, "UTF-8")); /*조회 기간 종료일*/
	        urlBuilder.append("&" + URLEncoder.encode("endHh","UTF-8") + "=" + URLEncoder.encode("23", "UTF-8")); /*조회 기간 종료시*/
	        urlBuilder.append("&" + URLEncoder.encode("stnIds","UTF-8") + "=" + URLEncoder.encode(areaArray[i], "UTF-8")); /*종관기상관측 지점 번호*/
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
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        String result = sb.toString();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
			JSONObject parse_response = (JSONObject) jsonObj.get("response");
			JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
			JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
			JSONArray parse_item = (JSONArray) parse_items.get("item"); // items 안에 item 배열
			
			System.out.println(parse_item);
			
			String queryString = "INSERT INTO tb_plant_radiation" + 
					"(pl_rad_date,pl_rad_location,pl_rad_icsr,pl_rad_ss,pl_rad_ssflg,pl_rad_ta,pl_rad_taflg)" + 
					" VALUES ";
			Connection con = null;
			PreparedStatement insert_pstm = null;
			int res = 0;
			
			con = getConnection();
			
			
			for (int j=0; j < parse_item.size(); j++) {
				
				JSONObject obj = (JSONObject) parse_item.get(j);
				if(j==0) {
					queryString += "('";
				}else {
					queryString += ",('";
				}
				queryString += (obj.get("tm")).toString();
				queryString += "', '";
				queryString += (obj.get("stnNm")).toString();
				queryString += "', '";
				queryString += (obj.get("icsr")).toString();
				queryString += "', '";
				queryString += (obj.get("ss")).toString();
				queryString += "', '";
				queryString += (obj.get("ssQcflg")).toString();
				queryString += "', '";
				queryString += (obj.get("ta")).toString();
				queryString += "', '";
				queryString += (obj.get("taQcflg")).toString();
				queryString += "')";
			}
			System.out.println(queryString);
			try {
				insert_pstm = con.prepareStatement(queryString);
				res = insert_pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("3 or 4 error");
				e.printStackTrace();
			} finally {
				close(insert_pstm, con);
			}
			
    	}
    }

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");

		String jdbcDriver = "jdbc:mysql://kangk0269.cafe24.com:3306/kangk0269?" + "useUnicode=true&characterEncoding=utf-8";
		String dbUser = "kangk0269";
		String dbPass = "kang0269";
		Connection conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);	
		return conn;
	}

	public static void close(PreparedStatement insert_pstm, Connection con) {
		try {
			insert_pstm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
