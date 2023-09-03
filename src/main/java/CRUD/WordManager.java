package CRUD;

import java.util.Scanner;

public class WordManager {
    Scanner s =new Scanner(System.in);
    WordCRUD wordCRUD;

    WordManager(){
        wordCRUD = new WordCRUD(s);
    }
    public int selectMenu(){
        System.out.println("*** 영단어 마스터 ***");
        System.out.print("*********************\n1. 모든 단어 보기\n2. 수준별 단어 보기\n3. 단어 검색\n4. 단어 추가\n5. 단어 수정\n6. 단어 삭제\n7. 파일 저장\n0. 나가기\n*********************\n=> 원하는 메뉴는? ");
        return s.nextInt();
    }
    public void start(){
        while(true) {
            int menu = selectMenu();
            if(menu==0) {//나가기
                break;
            }
            else if(menu == 1){//전체 보기

            }
            else if(menu == 2){//수준별 보기

            }
            else if(menu == 3){//검색

            }
            else if(menu == 4){//추가
               wordCRUD.addWord();

            }
            else if(menu == 5){//단어 수정

            }
            else if(menu == 6){//삭제

            }
            else if(menu == 7){//저장

            }
            else{

            }

        }
    }
}