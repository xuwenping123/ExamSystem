package models.vo;


public class QuestionAddScore {

	public long question_id;
	
	public String content;
	
	public String answer;
	
	public int type;
	
	public long subject_id;
	
	public int question_score;

	public QuestionAddScore(long question_id, String content, String answer, int type, long subject_id,
			int question_score) {
		this.question_id = question_id;
		this.content = content;
		this.answer = answer;
		this.type = type;
		this.subject_id = subject_id;
		this.question_score = question_score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public int getQuestion_score() {
		return question_score;
	}

	public void setQuestion_score(int question_score) {
		this.question_score = question_score;
	}
	
}
