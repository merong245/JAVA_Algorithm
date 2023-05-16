문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 광물캐기


### 문제 이해하기
1. 광물과 곡괭이에 따라 피로도가 다름
2. 한 곡괭이로 5개의 광물을 채취
2. 최소의 피로도로 곡괭이를 다쓰거나 광물을 다 캐고 싶음

### 문제 접근 방법
1. 문자열을 수치화 -> dia : 0, iron : 1, stone : 2
2. 광물의 수와 곡괭이 수가 적음 -> 완탐 가능!
3. dfs로 곡괭이의 선택가능한 중복조합 구하기
4. 곡괭이로 광물 채취하기 5개씩 -> 인덱스와 광물 수 고려
5. 곡괭이와 광물의 값을 비교하여 피로도를 구하고 최소값 갱신

### 구현 배경 지식
1. 중복조합
2. dfs
3. Map

### 접근 방법을 적용한 코드
```java
// 50, 5*5*5 -> 완탐
import java.util.*;
class Solution {
    static int[] selected;
    static int[] picks;
    static int[] minerals;
    static int picksCnt, len, answer = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        // 전역 변수로 이전
        this.picks = picks;
        this.minerals = new int[minerals.length];
        
        // 문자열 수치화
        for(int i = 0 ; i < minerals.length; i++){
            switch (minerals[i]){
                case "diamonds" :
                    this.minerals[i] = 0; 
                    break;
                case "iron":
                    this.minerals[i] = 1;
                    break;
                case "stone" :
                    this.minerals[i] = 2;
                    break;
            }
        }
            
        // 저장할 길이
        len = minerals.length/5+1;
        // 곡괭이 총 개수
        for(int i =0; i < 3; i++ ){
            picksCnt += picks[i];
        }
        selected = new int[len];
        // dfs로 중복 조합
        choose(0,0);
        return answer;
    }
    
    public void choose(int curr, int cnt){
        if(cnt == Math.min(len,picksCnt)){
            calc();
            return;
        }
        for(int i =0; i<3; i++){
            if(picks[i] > 0){
                picks[i]--;
                selected[cnt] = i;
                choose(curr+1,cnt+1);
                picks[i]++;
            }
            
        }   
    }
    public void calc(){
        int index = 0;
        int result = 0;
        for(int i =0; i < Math.min(len,picksCnt); i++){
            for(int j = 0; j< 5 && index < minerals.length ;j++,index++){
                // 미네랄 값과 비교해서 크거나 같은 경우 1
                if(selected[i] <= minerals[index]){
                    result+= 1;
                }
                // 미네랄이 더 작은 경우 5의 제곱수 만큼 피로도
                else if(selected[i] > minerals[index]){
                    result += Math.pow(5,selected[i] - minerals[index]); 
                }
            }
        }
        // 피로도 갱신
        answer = Math.min(answer,result);
    }
}
```