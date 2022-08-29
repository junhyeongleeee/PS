package javaCode.Programmers.kakao2021;

/**
 * 문제 : 신규 아이디 추천
 * 해설 : 구현
 */

class Solution2 {
    public String solution(String new_id) {
        String answer = new_id;

        // 소문자 치환
        String step1 = answer.toLowerCase();

        // 알파벳 소문자, 숫자, -, _, 마침표를 제외한 모든 문자 제거
        String step2 = "";
        for(int i=0;i<step1.length();i++){
            char ch = step1.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                step2 += String.valueOf(ch);
            }else if(ch >= '0' && ch <= '9'){
                step2 += String.valueOf(ch);
            }else if(ch == '-' || ch == '_' || ch == '.'){
                step2 += String.valueOf(ch);
            }
        }
        // 마침표(.)가 2번 이상 연속된 부분을 하나의 마치표로 치환
        String step3 = step2;
        while(step3.contains("..")){
            step3 = step3.replace("..", ".");
        }

        // 마침표가 처음이나 끝에 위치한다면 제거
        String step4 = step3;
        if(step4.startsWith("."))
            step4 = step4.substring(1, step4.length());

        if(step4.endsWith("."))
            step4 = step4.substring(0, step4.length() - 1);

        // 빈 문자열이면 a 대입
        String step5 = step4;
        if(step5.length() == 0) step5 = "a";

        // 16자 이상이면 첫 15개의 문자를 제외한 모든 문자 제거
        String step6 = step5;
        if(step6.length() >= 16)
            step6 = step6.substring(0, 15);

        if(step6.endsWith("."))
            step6 = step6.substring(0, step6.length() - 1);

        // 길이가 2자 이하면 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 붙인다.
        String step7 = step6;
        String last = String.valueOf(step7.charAt(step7.length() - 1));

        if(step7.length() <= 2){
            while(step7.length() != 3){
                step7 += last;
            }
        }

        return step7;
    }
}