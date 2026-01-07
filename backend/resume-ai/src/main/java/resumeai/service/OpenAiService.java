package resumeai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import resumeai.model.ResumeResult;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class OpenAiService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper mapper = new ObjectMapper();

    public ResumeResult analyzeResume(String resumeText) {

        try {
            return callOpenAI(resumeText);   // 🔥 Real AI
        } catch (Exception e) {
            System.out.println("AI failed, using dummy response: " + e.getMessage());
            return dummyResult();           // 🔁 Fallback
        }
    }

    // ================= REAL AI =================
    private ResumeResult callOpenAI(String resumeText) throws Exception {

        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("OPENAI_API_KEY missing");
        }

        String prompt = """
You are an ATS resume analyzer.
Return ONLY valid JSON.

{
  "score": number,
  "status": "Excellent | Good | Average | Poor",
  "matchedSkills": [],
  "suggestions": []
}

Resume:
""" + resumeText;

        String body = """
{
  "model": "gpt-4o-mini",
  "messages": [
    {
      "role": "user",
      "content": %s
    }
  ]
}
""".formatted(mapper.writeValueAsString(prompt));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(response.body());

        // ❌ OpenAI error handling
        if (root.has("error")) {
            throw new RuntimeException(root.get("error").get("message").asText());
        }

        String content = root.at("/choices/0/message/content").asText();
        JsonNode json = mapper.readTree(content);

        return new ResumeResult(
                json.get("score").asInt(),
                json.get("status").asText(),
                mapper.convertValue(json.get("matchedSkills"), List.class),
                mapper.convertValue(json.get("suggestions"), List.class)
        );
    }

    // ================= DUMMY RESULT =================
    private ResumeResult dummyResult() {
        return new ResumeResult(
                82,
                "Good",
                List.of("Java", "Spring Boot", "HTML", "CSS", "SQL"),
                List.of(
                        "Add more real-world projects",
                        "Use ATS-friendly keywords",
                        "Include measurable achievements"
                )
        );
    }
}
