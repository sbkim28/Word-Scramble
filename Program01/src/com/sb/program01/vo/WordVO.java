package com.sb.program01.vo;

public class WordVO {

	private String word;
	private String meaning;
	private String scrambleWord;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getScrambleWord() {
		return scrambleWord;
	}
	public void setScrambleWord(String scrambleWord) {
		this.scrambleWord = scrambleWord;
	}
	
	@Override
	public String toString() {
		return "WordVO [word=" + word + ", meaning=" + meaning + ", scrambleWord=" + scrambleWord + "]";
	}
	
	
	
}
