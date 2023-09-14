package fr.diginamic.aqiprojectbackend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

final public class JpaQueries {
    private static final Logger logger =
            LoggerFactory.getLogger(JpaQueries.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String url;
    private static final String user;
    private static final String password;
    static{
        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle("application");
        url = resourceBundle.getString("spring.datasource.url");
        user = resourceBundle.getString("spring.datasource.username");
        password = resourceBundle.getString("spring.datasource.password");
    }
    private JpaQueries(){}
    public static void update(String sqlScript) throws SQLException{
        try(Connection connection = DriverManager
                .getConnection(url,
                        user,
                        password)){
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlScript);
        }catch (SQLException ex){
            logger.error("Bad data query !", ex);
            throw ex;
        }
    }
    public static <T> List<T>
    extractEntities(String urlSpec) throws IOException{
        final String response = readFromUrlConnection(urlSpec);
        return objectMapper.readValue(response,
                new TypeReference<List<T>>(){});
    }
    public static <T> T
    extractEntities(String urlSpec, Class<T> cls) throws IOException{
        final String response = readFromUrlConnection(urlSpec);
        return objectMapper.readValue(response, cls);
    }

    public static String getUrl(){
        return url;
    }
    public static String getUser(){
        return user;
    }
    public static String getPassword(){
        return password;
    }
    private static String
    readFromUrlConnection(String urlSpec) throws IOException{
        final URL entitiesURL = new URL(urlSpec);
        final HttpURLConnection httpUrlConnection =
                (HttpURLConnection) entitiesURL.openConnection();
        httpUrlConnection.setRequestMethod("GET");
        String response;
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpUrlConnection.getInputStream()))){
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            response = stringBuilder.toString();
        }catch(IOException ex){
            logger.error("Failure or interruption in HTTP connection!", ex);
            throw ex;
        }
        return response;
    }
}
