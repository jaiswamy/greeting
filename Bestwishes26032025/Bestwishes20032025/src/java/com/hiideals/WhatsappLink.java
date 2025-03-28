package com.hiideals;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WhatsappLink {
    public static void sendMessage(String contactName, String certificateLink, String phoneNumber) {
        try {
            // Webbx API URL
            String apiUrl = "https://sent.wbbox.in/pinwa/pinwav1.php"; 
            String apiKey = "YOUR_CORRECT_API_KEY";  
            String fromPhoneNumber = "918788389212";  
            String templateId = "755113";  

            // Encode placeholders to handle special characters
            String encodedContactName = URLEncoder.encode(contactName, "UTF-8");
            String encodedCertificateLink = URLEncoder.encode(certificateLink, "UTF-8");

            // **Ensure Exactly Two Placeholders (No Extra Pipes "|")**
            String placeholders = encodedContactName + "|" + encodedCertificateLink;

            // Construct API Query Parameters
            String jsonPayload = "apikey=" + apiKey
                    + "&from=" + fromPhoneNumber
                    + "&to=" + phoneNumber
                    + "&type=template"
                    + "&templateid=" + templateId
                    + "&placeholders=" + placeholders;

            // Create API Request
            URL url = new URL(apiUrl + "?" + jsonPayload);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Get Response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("WhatsApp message sent successfully!");
            } else {
                System.out.println("Failed to send message. Response Code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 /*   public static void main(String[] args) throws UnsupportedEncodingException {
        // Example Dynamic Values
        String contactName = "John Doe";  
        String certificateLink = "https://example.com/certificate.pdf";  
        String phoneNumber = "918073492977";  

        // Send WhatsApp Message
        sendMessage("John Doe", "https://example.com/certificate.pdf";, phoneNumber);
    }*/
}
