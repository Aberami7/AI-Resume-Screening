package resumeai.model;

import java.util.List;

public class ResumeResult {

    private int score;
    private String status;
    private List<String> matchedSkills;
    private List<String> suggestions;

    public ResumeResult() {} // default constructor for Jackson

    public ResumeResult(int score, String status, List<String> matchedSkills, List<String> suggestions) {
        this.score = score;
        this.status = status;
        this.matchedSkills = matchedSkills;
        this.suggestions = suggestions;
    }

    public int getScore() { return score; }
    public String getStatus() { return status; }
    public List<String> getMatchedSkills() { return matchedSkills; }
    public List<String> getSuggestions() { return suggestions; }

    public void setScore(int score) { this.score = score; }
    public void setStatus(String status) { this.status = status; }
    public void setMatchedSkills(List<String> matchedSkills) { this.matchedSkills = matchedSkills; }
    public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }
}
