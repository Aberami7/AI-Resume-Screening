package resumeai.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class AiSuggestionService {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Object> generateSuggestions(String resumeText) throws IOException {

        // ✅ Prompt for ATS analysis
        String prompt =
                "You are an ATS resume analyzer.\n" +
                "Analyze the following resume and give:\n" +
                "1. Missing skills\n" +
                "2. ATS optimization tips\n" +
                "3. Resume improvement suggestions\n\n" +
                "Resume:\n" + resumeText;

        // ✅ Prepare JSON body safely using Jackson
        String requestBody = mapper.writeValueAsString(Map.of(
                "model", "gpt-4o-mini",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are a professional resume reviewer"),
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.3
        ));

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(OPENAI_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .build();

        // ✅ Execute request safely with try-with-resources
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("OpenAI API error: " + response.code() + " " + response.message());
            }

            String responseBody = response.body().string();

            // ✅ Parse JSON and return as Map
            return mapper.readValue(responseBody, Map.class);
        }
    }
}
