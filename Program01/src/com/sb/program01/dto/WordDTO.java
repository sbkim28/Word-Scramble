package com.sb.program01.dto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sb.program01.vo.WordVO;

public class WordDTO {
	
	private ArrayList<WordVO> wordDTO = new ArrayList<>();

	public ArrayList<WordVO> getWordDTO() {
		return wordDTO;
	}

	public void setWordDTO(ArrayList<WordVO> wordDTO) {
		this.wordDTO = wordDTO;
	}
	
	public void Reader() {
		
		try{
			MongoClient mongoClient = new MongoClient("localhost",27017);
			
			MongoDatabase db = mongoClient.getDatabase("mylib");
			
			MongoCollection<Document> collection = db.getCollection("words");
			System.out.println("MongoDB 접속 완료");
			MongoCursor<Document> cursor = collection.find().iterator();
			
			while(cursor.hasNext()){
				Document document = cursor.next();
				WordVO _tmp = new WordVO();
				_tmp.setWord(document.getString("word")); 
				_tmp.setMeaning(document.getString("meaning"));
				wordDTO.add(_tmp);
			}
			cursor.close();
			System.out.println("값 저장 완료");
			mongoClient.close();
			
		}catch (MongoException e) {
    		e.printStackTrace();
    	}
		System.out.println("========================================");
	}
	
	public WordVO Scramble(){
		
		Collections.shuffle(wordDTO);
		WordVO wordVO = new WordVO();
		wordVO = wordDTO.get(0);
		String[] _tmp = wordVO.getWord().split("");
		ArrayList<String> _array = new ArrayList<>();
		
		for (int i = 0; i < _tmp.length; i++) {
			_array.add(_tmp[i]);
		}
		Collections.shuffle(_array);
		wordVO.setScrambleWord(_array.toString());
		
		return wordVO;
	}
	
	public int IntRandom() {
		
		ArrayList<Integer> _array = new ArrayList<>();
		
		for (int i = 0; i < 2; i++){
			int _tmp = i + 1;
			_array.add(_tmp);
		}
		Collections.shuffle(_array);
		int r = _array.get(0);
		return r;
	}
	public void front(WordVO a) {
		WordVO _word = a;
		
		String[] _tmp = _word.getWord().split("");
		
		if (_tmp.length < 6) {
			System.out.println(_tmp[0]);
		}
		if (_tmp.length > 5 && _tmp.length < 9){
			System.out.print(_tmp[0]);
			System.out.println(_tmp[1]);
		}
		if (_tmp.length > 8) {
			System.out.print(_tmp[0]);
			System.out.print(_tmp[1]);
			System.out.println(_tmp[2]);
		}
	}
	
	
}
