package com.sb.program01.exec;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.sb.program01.dto.RecordDTO;
import com.sb.program01.dto.WordDTO;
import com.sb.program01.vo.RecordVO;
import com.sb.program01.vo.WordVO;


public class WordGame {

	public static void main(String[] args) {
		WordDTO  wordDTO = new WordDTO();
		WordVO wordVO = new WordVO();
		ArrayList<RecordVO> recordVO = new ArrayList<>();
		RecordDTO recordDTO = new RecordDTO();
		Scanner scanner = new Scanner(System.in);
		wordDTO.Reader();
		int hintRock = 3;
		int point = 0;
		int life = 0;
		boolean whilebreaker = false;
		while(whilebreaker == false){
			String _checker = null;
			wordVO = wordDTO.Scramble();
			for (int i = 0; i < 3 ;){
				
				System.out.println("������ �ܾ �ùٸ��� �迭�ϼ���.");
				System.out.println("(���� -QUIT, ��Ʈ -HINT)");
				System.out.print("Life:");
				System.out.println(3 - life);
				System.out.println("����� �� �ִ� ��Ʈ :" + hintRock);
				System.out.print(wordVO.getScrambleWord());
				System.out.print("\n");
				
				_checker = scanner.nextLine();
				if ( _checker.equalsIgnoreCase("-QUIT")){
					break;
				}
				if (_checker.equalsIgnoreCase("-Hint")){
					if(i > 0 && hintRock > 0) {
						if(wordDTO.IntRandom() == 1) {
							System.out.println("��Ʈ ");
							System.out.print("�ܾ��� ���� '");
							System.out.println(wordVO.getMeaning() + "'�Դϴ�.");
							hintRock--;
						} else {
							System.out.println("��Ʈ ");
							System.out.println("�ܾ��� �ձ��ڴ� ");
							wordDTO.front(wordVO);
							hintRock--;
						}
					} else {
						System.out.println("������ ��Ʈ�� ����� �� �����ϴ�. �ٽ� ������ �ּ���.");
					}
				} else {
					if (_checker.equalsIgnoreCase(wordVO.getWord())){
						System.out.println("�����Դϴ�.");
						System.out.print("�ܾ��� ���� '");
						System.out.println(wordVO.getMeaning() +"'�Դϴ�.");
						point++;
						System.out.print("���ݱ��� ���� �ܾ��� �� : ");
						System.out.println(point);
						if(point % 10 == 0){
							System.out.println("��Ʈ 1���� ���޵Ǿ����ϴ�.");
							hintRock++;
						}
						break;
					} else {
						System.out.println("�����Դϴ�.");
						if ( i == 2 && life < 3) {
							System.out.println("���� ��ȸ�� �����ϴ�.");
							System.out.print("������ '");
							System.out.println(wordVO.getWord() + "'�Դϴ�.");
							System.out.print("�ܾ��� ���� '");
							life++;
							System.out.println(wordVO.getMeaning() +"'�Դϴ�.");
							System.out.print("���� ������ ");
							System.out.println( 3 - life + "�Դϴ�.");
							i++;
						}
						else {
							System.out.print("���� ��ȸ : ");
							System.out.println(2 - i);
							i++;
						}
					}
				}
			}
			if (life == 3) {
				whilebreaker = true;
			}
			if (_checker.equalsIgnoreCase("-QUIT")){
				whilebreaker = true;
			}
		}
		
		System.out.println("Game Over");
		System.out.print("����� ��ģ �� �ܾ��� ���� '");
		System.out.println(point +"'�Դϴ�.");
		System.out.println("����� ������ ����Ͻ÷��� �Ʒ��� �г����� �Է��� �ּ���.(������ ������ ��ĭ���� ���� �ּ���.)");
		System.out.println("���� �г����� �Էµ� �ÿ� �ְ��� ������ ��ϵ˴ϴ�.");
		String nickName=scanner.nextLine();
		
		if(nickName.equals("")){
			recordVO.addAll(recordDTO.recordRead());
		} else {
			recordDTO.recordWrite(point, nickName);
			recordVO.addAll(recordDTO.recordRead());
			for(int i =0; i<recordVO.size();i++){
				if(nickName.equals(recordVO.get(i).getNickName())){
					System.out.print("����� ���� : ");
					System.out.println(i+1 + "��");
				}
			}
		}
		System.out.println("Top 5");
		for(int i=0;i<5;i++){
			try{
				System.out.println(i+1 + "." + recordVO.get(i).getNickName() + ":" + recordVO.get(i).getScore() + "��");
			}catch(Exception e){
				break;
			}
		}
		scanner.close();
	}

}
