/**
 * 
 */
package consoleQuiz;

/**
 * @author NathanClarke
 *
 */
public class Quiz {
	
	private String question;
	private String topic;
	private String[] ansArr;
	private String ans;
	private String correctAns;
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @return the ansArr
	 */
	public String[] getAnsArr() {
		return ansArr;
	}
	
	/**
	 * @param ansArr the ansArr to set
	 */
	public void setAnsArr(String[] ansArr) {
		this.ansArr = ansArr;
	}
	/**
	 * 
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * 
	 * @return provides the answer of a question, if it isn't multiple choice.
	 */
	public String getAns() {
		return ans;
	}
	
	/**
	 * @return the correctAns
	 */
	public String getCorrectAns() {
		return correctAns;
	}
	/**
	 * @param question
	 * @param topic
	 * @param ansArr
	 * @param correctAns
	 */
	public Quiz(String question, String topic, String[] ansArr, String correctAns) {
		super();
		this.question = question;
		this.topic = topic;
		this.ansArr = ansArr;
		this.correctAns = correctAns;
	}
	/**
	 * @param question
	 * @param topic
	 * @param ans
	 */
	public Quiz(String question, String topic, String ans) {
		super();
		this.question = question;
		this.topic = topic;
		this.ans = ans;
	}
	
	

}
