/**
 * 
 */
package consoleQuiz;

/**
 * @author NathanClarke
 *
 */

import java.util.*;
import java.io.*;

public class ConsoleQuiz {

	/**
	 * @param args
	 */
	private static List<String> topicList = new ArrayList<String>();
	private static List<Quiz> qList;
	private static Scanner scnr = new Scanner(System.in);
	private static File toTxt = new File("src/userLog.txt");
	private static BufferedWriter bW;
	
	public static void main(String[] args) {
		boolean flag = true;
		try {
			if(toTxt.exists()) {
				FileWriter fW = new FileWriter(toTxt, true);
				bW = new BufferedWriter(fW);
			}
			else {
				toTxt.createNewFile();
				FileWriter fW = new FileWriter(toTxt);
				bW = new BufferedWriter(fW);
			}
		}
		catch(IOException iE) {
			System.out.println(iE);
		}
		while(flag) {
			try {
				System.out.println("Please enter your team name\n");
				String tName = "";
				
				if(scnr.hasNextLine()) {
					tName = scnr.nextLine();
				}
				
				bW.write("Team Name: " + tName);
				bW.newLine();
				
				qList = init();
				
				System.out.println("\n\nDo you wish to play the quiz? Input Y on the next line to continue or N to exit\n");
				if(scnr.hasNextLine()) {
					String a = scnr.nextLine();
					a = a.toUpperCase();
					switch(a) {
						case "Y":
							while(true) {
								System.out.println("\nPlease enter the name of the topic which you wish to play from the list below.");
								int i = 0;
								
								if(topicList.size() == 0) {
									congrats();
									break;
								}
								else {
									for(String s : topicList) {
										System.out.println(i + ") " + s);
										i++;
									}
								}
								
								String choice = "";
								
								if(scnr.hasNextLine()) {
									choice = scnr.nextLine();
								}
								
								int cCh = 0;
								
								for(String st: topicList) {
									if (choice.equals(st)){
										game(choice);
										cCh++;
										break;
									}
								}
								if(cCh == 0) {
									System.out.println("\nThat was an invalid choice\n");
								}
							}
							
							break;
						case "N":
							System.out.println("\n--EXITING GAME--");
							scnr.close();
							bW.close();
							flag = false;
							break;
						default:
							System.out.println("\nThat wasn't a valid response, try again.");
							break;
					}
					
					flag = false;
					scnr.close();
					bW.close();
				}
			}
			catch(Exception e) {
				System.out.println(e);
				flag = false;
			}
		}

	}
	
	private static void congrats() throws IOException {
		System.out.println("\nCongratulations, you have completed the quiz.\n");
		bW.write("--------------------------------");
		
	}

	private static List<Quiz> init() {
		
		// Initialises topics.
		topicList.add("Science");
		topicList.add("Technology");
		topicList.add("Games");
		topicList.add("Literature");
		
		List<Quiz> tempList = new ArrayList<Quiz>();
		
		
		// Initialises Questions & Answers
		// Science Q&A
		tempList.add(new Quiz("What is the chemical formula for salt?", "Science", "NaCl"));
		tempList.add(new Quiz("What does DNA stand for?", "Science", new String[] {"Deoxyribonucleic Acid", "Deoxyridonucleic Acid", "Discovery & Natural Adaptation", "Data of Navigation concerning Asteroids"}, "Deoxyribonucleic Acid"));
		tempList.add(new Quiz("What is the study of mushrooms called?", "Science", new String[] {"Fungology", "Spongology", "Mycology", "Senology"}, "Mycology"));
		tempList.add(new Quiz("At what temperature are Celsius & Fahrenheit equal?", "Science", "-40"));
		tempList.add(new Quiz("What is the predominant symbol for health care & medicine?", "Science", new String[] {"Caduceus", "Asklepian", "Bident", "Lyre"}, "Asklepian"));
		
		// Technology Q&A
		tempList.add(new Quiz("What does CPU stand for?", "Technology", "Central Processing Unit"));
		tempList.add(new Quiz("Which brothers invented the aeroplane?", "Technology", new String[] {"Wright Brothers","Mario Brothers","Right Brothers","White Brothers"}, "Wright Brothers"));
		tempList.add(new Quiz("In what year was Nintendo founded?", "Technology", "1889"));
		tempList.add(new Quiz("Who created the Java programming language?", "Technology", new String[] {"Guido van Rossum", "William Joy", "Linus Torvalds", "James Gosling"}, "James Gosling"));
		tempList.add(new Quiz("In what decade did the Pascal programming language first appear?", "Technology", new String[] {"60s", "70s", "80s", "50s"}, "70s"));
		
		// Games Q&A
		tempList.add(new Quiz("What is the first commercial home video game console?", "Games", new String[] {"Magnavox Odyssey", "Atari 2600", "ColecoVision", "NES"}, "Magnavox Odyssey"));
		tempList.add(new Quiz("In chess, how many pieces are in a set?", "Games", "16"));
		tempList.add(new Quiz("In what country did the card game Conquian originate?", "Games", new String[] {"Uruguay", "Spain", "Mexico", "China"}, "Mexico"));
		tempList.add(new Quiz("In what year was the first Halo game released?", "Games", "2001"));
		tempList.add(new Quiz("What was the first video game to be played in space?", "Games", "Tetris"));

		// Literature Q&A
		tempList.add(new Quiz("In Dante Alighieri's Divine Comedy, how many circles of Hell were there?", "Literature", new String[] {"6", "7", "8", "9"}, "9"));
		tempList.add(new Quiz("Who typically accompanies Sherlock Holmes?", "Literature", "John Watson"));
		tempList.add(new Quiz("Who created the Russian novella 'Вий'?", "Literature", new String[] {"Nikolai Gogol", "Fyodor Dostoevsky", "Alexander Pushkin", "Vladimir Solovyov"}, "Nikolai Gogol"));
		tempList.add(new Quiz("Which country banned Alice in Wonderland?", "Literature", new String[] {"Argentina", "Russia", "China", "North Korea"}, "China"));
		tempList.add(new Quiz("The character Othello in Shakespeare's tragedy served as a commander of what race?", "Literature", "Moorish"));
		
		return tempList;
	}
	
	private static void game(String topic) throws IOException {
		String ch = topic;
		List<Quiz> tempQList = new ArrayList<Quiz>();
		
		for(Quiz q : qList) {
			if(q.getTopic().equals(ch)) {
				tempQList.add(q);
			}
		}
		
		for(Quiz qu : tempQList) {
			System.out.println("Q: " + qu.getQuestion() + "\n");
			
			bW.write("Q: " + qu.getQuestion());
			bW.newLine();
			
			int usedFifty = 0;
			
			if(qu.getAnsArr()  != null) {
				while(true) {
					String[] tempAns = qu.getAnsArr();
					//List<Integer> indexesRemove = new ArrayList<Integer>();
					int i = 0;
					
					for(String a : tempAns) {
						System.out.println(i + ") " + a);
						i++;
					}
					
					System.out.println("\nPlease input the index of the correct answer. If you wish to 50/50, input 5050.");
					int chosenIndex = 0;
					
					if(scnr.hasNextInt()) {
						chosenIndex = scnr.nextInt();
						scnr.nextLine();
					}
					
					if(chosenIndex == 5050 && tempAns.length > 2) {
						Random rnd = new Random();
						int x = 0;
						int count = 0;
						int maxNum = 4;
						String corrAns = qu.getCorrectAns();
						
						
						
						ArrayList<String> ansList = new ArrayList<String>(Arrays.asList(tempAns));
						
						while(count < 2) {
							x = rnd.nextInt(maxNum);
							
							if(!(ansList.get(x).equals(corrAns))) {
								ansList.remove(x);
								//indexesRemove.add(x);
								//System.out.println(x);
								count++;
								maxNum--;
								usedFifty++;	
							}
						}
						
						String newArr[] = new String[ansList.size()];
						
						for(int j = 0; j < ansList.size(); j++) {
							newArr[j] = ansList.get(j);
						}
						
						qu.setAnsArr(newArr);
					}
					else if(chosenIndex == 5050 && tempAns.length <= 2) {
						System.out.println("\nYou have already used the 5050 on this question.\n");
					}
					else {
						if(!(chosenIndex > (tempAns.length - 1)) && tempAns[chosenIndex].equals(qu.getCorrectAns())) {
							System.out.println("\nCorrect!\n");
							
							bW.write("User Answer: " + tempAns[chosenIndex] + " || Correct Choice");
							bW.newLine();
							
							switch(usedFifty) {
								default:
									bW.write("*** 5050 used for this question ***");
									bW.newLine();
									bW.newLine();
									break;
								case 0:
									bW.write("*** 5050 unused for this question ***");
									bW.newLine();
									bW.newLine();
									break;
							}
							break;
						}
						else {
							System.out.println("\nIncorrect, try again\n");
							
							if(chosenIndex > (tempAns.length - 1)) {
								bW.write("Given answer index that doesn't exist || Incorrect answer.");
								bW.newLine();
							}
							else {
								bW.write("User Answer: " + tempAns[chosenIndex] + " || Incorrect Answer");
								bW.newLine();
							}
						}
					}
				}
			}
			else {
				while(true) {
					System.out.println("Please input the answer.\n");
					String uAns = "";
					if(scnr.hasNextLine()) {
						uAns = scnr.nextLine();
					}
					
					if(uAns.equals(qu.getAns())) {
						System.out.println("\nCorrect!\n");
						
						bW.write("User Answer: " + uAns + " || Correct Choice");
						bW.newLine();
						break;
					}
					else {
						System.out.println("\nIncorrect, try again\n");
						bW.write("User Answer: " + uAns + " || Incorrect Answer");
						bW.newLine();
					}
				}
			}
			
			topicList.remove(ch);
		}
	}

}
