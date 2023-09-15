package CRUD;



import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;


public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    final String fname="test.txt";
    WordCRUD(Scanner s){
        list=new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        int level;
        String word;
        String meaning;
        int a=0;
        while(true) {
            System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
            level = s.nextInt();
            word = s.nextLine();//버퍼를 없애기 위하여
            word = word.trim();
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            //띄어쓰기 제거 이후 앞부분을 대문자로 변형
            System.out.print("뜻 입력 : ");
            meaning = s.nextLine();//공백까지 모두 입력받기 위해서
            for(int i=0; i<list.size(); i++){
                if(word.toLowerCase().contains(list.get(i).getWord().toLowerCase())){
                    a=1;
                    System.out.println("이미 존재합니다. 다시 입력하세요.");
                    break;
                }
            }
            if(a==0) {
                break;
            }
            else{
                a=0;
            }
        }

        return new Word(0, level, word, meaning);
    }

    public void addItem(){
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다!!!\n");

    }


    @Override
    public void selectOne(int id) {

    }

    public void listAll(){

        System.out.println("-------------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.printf("%-3d",i+1);
            System.out.println(list.get(i).toString());
        }
        System.out.println("-------------------------------");
    }

    public ArrayList<Integer> listAll(String keyword){//overload
        ArrayList<Integer> idlist = new ArrayList<>();//왜 넣었는지 물어보자
        int j=0;

        System.out.println("-------------------------------");
        for(int i=0; i<list.size(); i++){
            String word = list.get(i).getWord();

            if(!word.toLowerCase().contains(keyword.toLowerCase())) continue;
            System.out.print((j+1)+" ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        if(j==0){
            System.out.println("유사단어 소개");
            for(int i=0; i<list.size(); i++){
                String word2=list.get(i).getWord();
                int c=keyword.length();
                int c2=0;

                for(int s=0; s<c; s++){
                    if(keyword.length() == word2.length() && keyword.charAt(s)==word2.charAt(s)){
                        c2++;
                    }
                }
                if(keyword.length() == word2.length() && c2>=c-2){
                    System.out.print((j+1)+" ");
                    System.out.println(list.get(i).toString());
                    j++;
                }
            }
        }
        System.out.println("-------------------------------");
        return idlist;
    }

    public void listAll(int level){
        int j=0;
        System.out.println("-------------------------------");
        for(int i=0; i<list.size(); i++){
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j+1)+" ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("-------------------------------");
    }

    public void updateItem() {
        System.out.print("수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print(" =>수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("\n단어 수정이 성공적으로 되었습니다.\n");
    }

    public void deleteItem() {
        System.out.print("삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print(" =>삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("=> 정말로 삭제하실래요?(Y/N) ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("Y")){
            list.remove((int)idlist.get(id-1));
            System.out.println("\n선택한 단어 삭제 완료 !!!\n.");
        }
        else{
            System.out.println("취소되었습니다.");
        }
    }

    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            String next;
            int count = 0;

            while(true) {
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                word=word.trim();
                word=word.toLowerCase();
                word=word.substring(0,1).toUpperCase() + word.substring(1);
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }


            br.close();
            System.out.println("==>" + count +"개 로딩 완료!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
            for(Word one : list){
                pr.write(one.toFileString()+"\n");
            }


            pr.close();
            System.out.println("\n모든 단어 파일 저장 완료 !!!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.print("=> 레벨(1:초급, 2:중급, 3.고급) 선택 : ");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String keyword = s.next();
        listAll(keyword);
    }
}
