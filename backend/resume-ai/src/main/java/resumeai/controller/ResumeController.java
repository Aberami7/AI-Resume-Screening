package resumeai.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import resumeai.model.ResumeResult;
import resumeai.service.OpenAiService;
import resumeai.service.PdfService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ResumeController {

    private final PdfService pdfService;
    private final OpenAiService openAiService;

    public ResumeController(PdfService pdfService,
                            OpenAiService openAiService) {
        this.pdfService = pdfService;
        this.openAiService = openAiService;
    }

    @PostMapping("/analyzeResume")
    public ResumeResult analyzeResume(@RequestParam("resume") MultipartFile file) {
        try {
            // ✅ Debug: check file received
            System.out.println("Received file: " + file.getOriginalFilename() + ", size: " + file.getSize());

            if (file.isEmpty()) {
                throw new RuntimeException("Resume missing");
            }

            // ✅ Extract text
            String text = pdfService.extractText(file);
            if (text == null || text.trim().isEmpty()) {
                throw new RuntimeException("PDF text extraction failed or empty PDF");
            }
            System.out.println("Extracted text length: " + text.length());

            // ✅ Analyze resume using AI service
            ResumeResult result = openAiService.analyzeResume(text);
            System.out.println("Score: " + result.getScore() + ", Status: " + result.getStatus());

            return result;

        } catch (Exception e) {
            // ✅ Print full stack trace for debugging
            e.printStackTrace();

            // Return a safe response so frontend knows analysis failed
            ResumeResult errorResult = new ResumeResult();
            errorResult.setScore(0);
            errorResult.setStatus("Failed: " + e.getMessage());
            return errorResult;
        }
    }
}
