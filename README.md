<img width="1920" height="1020" alt="Screenshot 2026-01-07 102431" src="https://github.com/user-attachments/assets/fc760a2a-9c0a-41ce-b70b-0e5b737e6cf0" />AI Resume Screening System

An AI-powered ATS (Applicant Tracking System) that analyzes resumes, generates a shortlisting score, identifies matched skills, and provides personalized improvement suggestions. This project demonstrates how modern recruitment platforms automatically evaluate candidates and streamline hiring.


 Demo Preview:
 
<img width="1920" height="1020" alt="Screenshot 2026-01-07 102431" src="https://github.com/user-attachments/assets/fcb5a276-2993-42c2-801e-c2cdc648002d" />


<img width="1920" height="1020" alt="Screenshot 2026-01-07 102506" src="https://github.com/user-attachments/assets/a97e170c-77b3-4223-93e4-f32ad54d2a26" />

<img width="1920" height="1020" alt="Screenshot 2026-01-07 102523" src="https://github.com/user-attachments/assets/1fb9cfac-d92a-444e-b4d0-5dc60a4385ef" />

Screenshots show the complete resume upload, analysis, and report generation workflow.

 How It Works:

The system integrates with the OpenAI API to analyze resume content, match skills with job requirements, and calculate a scoring metric. For demo or testing purposes, a dummy fallback response is used to simulate AI output when API credits are limited. In production, it seamlessly switches to real AI analysis.

try {
    return callOpenAI(resumeText);
} catch (Exception e) {
    return dummyResult(); // fallback response for demo
}

Features:

Resume Upload & Parsing: Accepts PDF resumes.

Skill Matching: Highlights relevant skills based on job keywords.

Scoring & Suggestions: Generates ATS-style score and improvement tips.

Demo-Friendly: Works without API keys using fallback simulation.

