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
				
				System.out.println("다음의 단어를 올바르게 배열하세요.");
				System.out.println("(종료 -QUIT, 힌트 -HINT)");
				System.out.print("Life:");
				System.out.println(3 - life);
				System.out.println("사용할 수 있는 힌트 :" + hintRock);
				System.out.print(wordVO.getScrambleWord());
				System.out.print("\n");
				
				_checker = scanner.nextLine();
				if ( _checker.equalsIgnoreCase("-QUIT")){
					break;
				}
				if (_checker.equalsIgnoreCase("-Hint")){
					if(i > 0 && hintRock > 0) {
						if(wordDTO.IntRandom() == 1) {
							System.out.println("힌트 ");
							System.out.print("단어의 뜻은 '");
							System.out.println(wordVO.getMeaning() + "'입니다.");
							hintRock--;
						} else {
							System.out.println("힌트 ");
							System.out.println("단어의 앞글자는 ");
							wordDTO.front(wordVO);
							hintRock--;
						}
					} else {
						System.out.println("지금은 힌트를 사용할 수 없습니다. 다시 도전해 주세요.");
					}
				} else {
					if (_checker.equalsIgnoreCase(wordVO.getWord())){
						System.out.println("정답입니다.");
						System.out.print("단어의 뜻은 '");
						System.out.println(wordVO.getMeaning() +"'입니다.");
						point++;
						System.out.print("지금까지 맞춘 단어의 수 : ");
						System.out.println(point);
						if(point % 10 == 0){
							System.out.println("힌트 1개가 지급되었습니다.");
							hintRock++;
						}
						break;
					} else {
						System.out.println("오답입니다.");
						if ( i == 2 && life < 3) {
							System.out.println("남은 기회가 없습니다.");
							System.out.print("정답은 '");
							System.out.println(wordVO.getWord() + "'입니다.");
							System.out.print("단어의 뜻은 '");
							life++;
							System.out.println(wordVO.getMeaning() +"'입니다.");
							System.out.print("남은 생명은 ");
							System.out.println( 3 - life + "입니다.");
							i++;
						}
						else {
							System.out.print("남은 기회 : ");
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
		System.out.print("당신이 맞친 총 단어의 수는 '");
		System.out.println(point +"'입니다.");
		System.out.println("당신의 점수를 등록하시려면 아래에 닉네임을 입력해 주세요.(원하지 않을시 빈칸으로 남겨 주세요.)");
		System.out.println("같은 닉네임이 입력될 시에 최고의 점수만 기록됩니다.");
		String nickName=scanner.nextLine();
		
		if(nickName.equals("")){
			recordVO.addAll(recordDTO.recordRead());
		} else {
			recordDTO.recordWrite(point, nickName);
			recordVO.addAll(recordDTO.recordRead());
			for(int i =0; i<recordVO.size();i++){
				if(nickName.equals(recordVO.get(i).getNickName())){
					System.out.print("당신의 순위 : ");
					System.out.println(i+1 + "위");
				}
			}
		}
		System.out.println("Top 5");
		for(int i=0;i<5;i++){
			try{
				System.out.println(i+1 + "." + recordVO.get(i).getNickName() + ":" + recordVO.get(i).getScore() + "점");
			}catch(Exception e){
				break;
			}
		}
		scanner.close();
	}

}
