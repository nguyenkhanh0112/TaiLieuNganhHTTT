/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Entity.SinhVien;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Khoahihi79
 */
public class SinhVienService {
    
    private static final String BASE_URL = "http://localhost:8080/api/v1";
    
    public List<SinhVien> getAllSinhVien() throws IOException {
        List<SinhVien> listSinhVien = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/students");
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listSinhVien = new ObjectMapper().readValue(result.toString(), new TypeReference<List<SinhVien>> () {});
        return listSinhVien;
    }
    
    public List<SinhVien> getAllSinhVienByIdKhoa(String idKhoa) throws IOException {
        List<SinhVien> listSinhVien = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/students/s1/" + idKhoa);
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listSinhVien = new ObjectMapper().readValue(result.toString(), new TypeReference<List<SinhVien>> () {});
        return listSinhVien;
    }
    
    public List<SinhVien> getAllSinhVienByIdLop(String idLop) throws IOException {
        List<SinhVien> listSinhVien = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/students/s2/" + idLop);
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listSinhVien = new ObjectMapper().readValue(result.toString(), new TypeReference<List<SinhVien>> () {});
        return listSinhVien;
    }
    
    public List<SinhVien> getAllSinhVienByKeyword(String keyword) throws IOException {
        
        String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
        
        List<SinhVien> listSinhVien = new ArrayList<>();
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/students/search?keyword=" + encodedKeyword);
        
        CloseableHttpResponse response = client.execute(httpGet);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        
        listSinhVien = new ObjectMapper().readValue(result.toString(), new TypeReference<List<SinhVien>> () {});
        return listSinhVien;
    }
}
