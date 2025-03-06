/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class FAQ {
     private int faqID;       
    private String question; 
    private String answer;   

    public FAQ(int faqID, String question, String answer) {
        this.faqID = faqID;
        this.question = question;
        this.answer = answer;
    }

    public FAQ() {
    }

    public int getFaqID() {
        return faqID;
    }

    public void setFaqID(int faqID) {
        this.faqID = faqID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
