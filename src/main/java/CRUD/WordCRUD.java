package CRUD;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s){
        list=new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();//버퍼를 없애기 위하여
        word=word.substring(1,2).toUpperCase()+ word.substring(2);
        //작성시 띄어쓰기까지 포함시켜서 첫번째 부분은 공백이다. 그래서 0,1이 아닌 1,2가 된다.
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();//공백까지 모두 입력받기 위해서

        return new Word(0, level, word, meaning);
    }

    public void addWord(){
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다!!!\n");

    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        System.out.println("-------------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.print((i+1)+" ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-------------------------------");
    }
}
