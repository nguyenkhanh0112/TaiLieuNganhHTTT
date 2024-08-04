/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Entity.CauHoi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Khoahihi79
 */
public class CauHoiService {
    private static final String BASE_URL = "http://localhost:8080/api/v1";
    
    public CauHoi createCauHoi(CauHoi cauHoi) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL + "/add/question");
        httpPost.setHeader("Content-Type", "application/json");
        
        StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(cauHoi));
        httpPost.setEntity(entity);
        
        CloseableHttpResponse response = client.execute(httpPost);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder result = new StringBuilder();
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        return new ObjectMapper().readValue(result.toString(), CauHoi.class);
    }
    
    public CauHoi updateCauHoi(int id, CauHoi cauHoi) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut  = new HttpPut(BASE_URL + "/update/question?id=" + id);
        httpPut.setHeader("Content-Type", "application/json");

        StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(cauHoi), StandardCharsets.UTF_8);
        httpPut.setEntity(entity);
        
        CloseableHttpResponse response = client.execute(httpPut);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        return new ObjectMapper().readValue(result.toString(), CauHoi.class);
    }
    
    public boolean deleteCauHoi(int id) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(BASE_URL + "/delete/question?id=" + id);
        
        CloseableHttpResponse response = client.execute(httpDelete);
        return response.getStatusLine().getStatusCode() == 200;
    }
    
    public List<CauHoi> getAllCauHoi() throws IOException {
        List<CauHoi> listCauHoi = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/questions");
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listCauHoi = new ObjectMapper().readValue(result.toString(), new TypeReference<List<CauHoi>> () {});
        return listCauHoi;
    }
    
    public List<CauHoi> getAllCauHoiByIdMonThi(String idMonThi) throws IOException {
        List<CauHoi> listCauHoi = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/questions/q1?id=" + idMonThi); 
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listCauHoi = new ObjectMapper().readValue(result.toString(), new TypeReference<List<CauHoi>> () {});
        return listCauHoi;
    }
    
    public List<CauHoi> getAllCauHoiByIdDeThi(String idDeThi) throws IOException {
        List<CauHoi> listCauHoi = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/questions/q4?id=" + idDeThi); 
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listCauHoi = new ObjectMapper().readValue(result.toString(), new TypeReference<List<CauHoi>> () {});
        return listCauHoi;
    }
    
    public List<CauHoi> getAllCauHoiByIdMonThiAndIdChuong(String idMonThi, String idChuong) throws IOException {
        List<CauHoi> listCauHoi = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/questions/q2?id1=" + idMonThi + "&id2=" + idChuong);
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listCauHoi = new ObjectMapper().readValue(result.toString(), new TypeReference<List<CauHoi>> () {});
        return listCauHoi;
    }
    
    public List<CauHoi> getAllCauHoiIdMonThiAndIdMucDo(String idMonThi, String idMucDo) throws IOException {
        List<CauHoi> listCauHoi = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/questions/q3?id1=" + idMonThi + "&id2=" + idMucDo);
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listCauHoi = new ObjectMapper().readValue(result.toString(), new TypeReference<List<CauHoi>> () {});
        return listCauHoi;
    }
    
    public List<CauHoi> getAllCauHoiByKeyWord(String keyword) throws IOException{
        String encodedKeyWord = URLEncoder.encode(keyword, "UTF-8");
        
        List<CauHoi> listCauHoi = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/questions/searchBy?keyword=" + encodedKeyWord);
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listCauHoi = new ObjectMapper().readValue(result.toString(), new TypeReference<List<CauHoi>> () {});
        return listCauHoi;
    }
}
