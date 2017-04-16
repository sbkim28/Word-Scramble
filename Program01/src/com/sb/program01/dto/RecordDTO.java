package com.sb.program01.dto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.sb.program01.vo.RecordVO;

public class RecordDTO {
	private ArrayList<RecordVO> recordDTO = new ArrayList<>();
	public ArrayList<RecordVO> getRecordDTO() {
		return recordDTO;
	}

	public void setRecordDTO(ArrayList<RecordVO> recordDTO) {
		this.recordDTO = recordDTO;
	}
	
	
	public void recordWrite(int score, String nickName){
		String outFile = "src/com/sb/program01/data/record.txt";
		
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		FileReader fileReader = null;
		BufferedReader bufferReader = null;
	
		try {
			fileReader = new FileReader(outFile);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 읽는데 오류가 발생했습니다.");
			System.exit(1);
		}
		bufferReader = new BufferedReader(fileReader);
		ArrayList<RecordVO> readLine = new ArrayList<>();
		try {
			while(true){
				String cart = bufferReader.readLine();
				if(cart == null) {
					break;
				}
				String[] _tmp = cart.split("-");
				RecordVO record = new RecordVO();
				int scoreInt = Integer.parseInt(_tmp[0]);
				record.setScore(scoreInt);
				record.setNickName(_tmp[1]);
				readLine.add(record);
			}
		} catch (IOException e) {
			System.out.println("파일을 읽는데 실패했습니다.");
			System.exit(1);
		}
		try {
			bufferReader.close();
		} catch (IOException e) {
			System.out.println("파일을 닫는데 실패했습니다.");
			System.exit(1);
		}
		//파일 읽기
		try {
			fileWriter = new FileWriter(outFile);
		} catch (IOException e) {
			System.out.println("파일을 작성하는데 오류가 발생했습니다.");
			System.exit(1);
		}
		bufferWriter = new BufferedWriter(fileWriter);
		int tmp = 0;
		for(RecordVO v:readLine){
			if(v.getNickName().equals(nickName)){
				tmp = 1;
				if(v.getScore() < score){
					v.setScore(score);
				}
			}
		}
		if(tmp == 0){
			RecordVO record = new RecordVO();
			record.setScore(score);
			record.setNickName(nickName);
			readLine.add(record);
		}
		try {
			for(int i=0;i<readLine.size();i++){
				bufferWriter.write(readLine.get(i).getScore()+"");
				bufferWriter.write("-");
				bufferWriter.write(readLine.get(i).getNickName());
				bufferWriter.newLine();
			}
		} catch (IOException e) {
			System.out.println("파일을 작성하는데 오류가 발생했습니다.");
			System.exit(1);
		}
		try {
			bufferWriter.close();
		} catch (IOException e1) {
			System.out.println("파일을 닫는데 오류가 발생했습니다.");
			System.exit(1);
		}
	}
	
	public ArrayList<RecordVO> recordRead(){
		String inFile = "src/com/sb/program01/data/record.txt";
		FileReader fileReader = null;
		BufferedReader bufferReader = null;
	
		try {
			fileReader = new FileReader(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 읽는데 오류가 발생했습니다.");
			System.exit(1);
		}
		bufferReader = new BufferedReader(fileReader);
		
		try {
			while(true){
				String cart = bufferReader.readLine();
				if(cart == null) {
					break;
				}
				String[] _tmp = cart.split("-");
				RecordVO record = new RecordVO();
				int scoreInt = Integer.parseInt(_tmp[0]);
				record.setScore(scoreInt);
				record.setNickName(_tmp[1]);
				recordDTO.add(record);
			}
		} catch (IOException e) {
			System.out.println("파일을 읽는데 실패했습니다.");
			System.exit(1);
		}
		for(int i = 0; i < recordDTO.size(); i++){
			for(int j = i; j < recordDTO.size();j++){
				if(recordDTO.get(i).getScore() < recordDTO.get(j).getScore()){
					RecordVO _tmp = recordDTO.get(i);
					recordDTO.set(i, recordDTO.get(j));
					recordDTO.set(j, _tmp);
				}
			}
		}
		try {
			bufferReader.close();
		} catch (IOException e) {
			System.out.println("파일을 닫는데 실패했습니다.");
			System.exit(1);
		}
		return recordDTO;
	}
}
