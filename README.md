                                          AI Resume Screening System


An AI-powered ATS (Applicant Tracking System) that analyzes resumes, generates a shortlisting score, identifies matched skills, and provides personalized improvement suggestions. This project demonstrates how modern recruitment platforms automatically evaluate candidates and streamline hiring.


 Demo Preview:
 
<img width="1902" height="913" alt="Screenshot 2026-02-21 180308" src="https://github.com/user-attachments/assets/de101c00-01a0-47d3-9c4d-6570be41598c" />

<img width="1898" height="905" alt="Screenshot 2026-02-21 180547" src="https://github.com/user-attachments/assets/865adc11-3b42-4189-8b9a-75cc11bd3b76" />

<img width="1902" height="903" alt="Screenshot 2026-02-21 180558" src="https://github.com/user-attachments/assets/ddc42d4f-2a17-433e-a112-f87a419dc9d9" />

Screenshots show the complete resume upload, analysis, and report generation workflow.

Flow:

--> Resume Upload:
      Drag & drop PDF or use the Browse button.Max file size: 5MB, PDF only.Clean, intuitive UI for quick uploads.

--> Analysis Status:
      Shows real-time analysis progress.Displays completion status once done.

--> Score & Rating:
      ATS-style shortlisting score (e.g., 82%).Simple rating description: Good / Average / Poor.
      Visual emphasis on score for easy interpretation.

--> View Detailed Report:
      Lists Matched Skills automatically extracted from the resume.
      Shows ATS Suggestions for improvements, such as: Adding more real-world projects,Using ATS-friendly keywords,Including measurable achievements.

--> Navigation & Actions:
          Buttons for View Details, Upload Another Resume, and Back.Easy navigation between upload and detailed report screens.

How It Works:

The system integrates with the OpenAI API to analyze resume content, match skills with job requirements, and calculate a scoring metric. For demo or testing purposes, a dummy fallback response is used to simulate AI output when API credits are limited. In production, it seamlessly switches to real AI analysis.


                                                                  
                              try {
                                    return callOpenAI(resumeText);
                                } 
                                catch (Exception e)
                                {
                                     return dummyResult(); // fallback response for demo
                                }

Features:

-Resume Upload & Parsing: Accepts PDF resumes.

-Skill Matching: Highlights relevant skills based on job keywords.

-Scoring & Suggestions: Generates ATS-style score and improvement tips.

-Demo-Friendly: Works without API keys using fallback simulation








.

