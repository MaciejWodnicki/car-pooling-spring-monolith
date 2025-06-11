package org.pooling.service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

@Service
public class ReCaptchaServiceImpl implements ReCaptchaService {
    private static final String URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String GOOGLE_KEY = "6Lc0QFwrAAAAANtxMHB6rzNpjPd49cN4Ki1AQNrD";



    @Override
    public boolean verify(String captcha) {
        if (captcha == null || captcha.isEmpty()) return false;

        try {
            URL obj = new URL(URL);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            String postParams = "secret=" + GOOGLE_KEY + "&response=" + captcha;
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(postParams);
                wr.flush();
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine); // ✅ Append inside the loop
                }
            }

            String jsonResponse = response.toString();
            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                System.err.println("Empty JSON response from reCAPTCHA");
                return false;
            }

            JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success", false);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
