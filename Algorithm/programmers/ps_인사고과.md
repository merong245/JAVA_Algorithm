문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 인사고과


### 문제 이해하기
1. 각 사원은 2가지의 평가 점수를 받는다.
2. 두 점수가 다른 사람보다 모두 낮을 때 해당 사원은 인센티브를 받지 못한다.
3. 동일 석차의 경우 같은 등수를 받고 1등이 세명이라면 2,3등없이 다음 등수는 4등이다.
4. 이때 원호가 받는 인센티브를 구해라


### 문제 접근 방법
1. 두 값의 합이 크면 절대 두 값이 다른 값보다 작을 수 없다. 즉 인센티브를 받는다.
2. 우선 순위 큐를 이용하여 합을 기준으로 내림차순 정렬한다.
3. 우선 순위 큐의 값을 배열의 값과 비교하며 처리해준다.
4. 두 값이 우선순위 큐의 값이 크다면 순위에 반영한다.
5. 만약 합이 원호랑 같다면 원호와 동일 등수이므로 증가시키지 않는다.
6. 그 외에는 순위 반영한다.
7. 우선 순위 큐의 값이 작다면 인센티브를 받지 못하므로 순위에 반영하지 않는다.
8. 만약 원호라면 -1을 출력한다.

### 구현 배경 지식
1. 우선 순위 큐

### 접근 방법을 적용한 코드
```java

import java.util.*;

class Emp implements Comparable<Emp>{
    int x, y;
    Emp (int x, int y){
        this.x = x;
        this.y= y;
    }
    public int compareTo(Emp o){
        return Integer.compare(o.x+o.y,x+y);
    }
}

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int n = scores.length;
        
        PriorityQueue<Emp> pq = new PriorityQueue<Emp>();
        
        for(int i = 0; i < n;i++){
            pq.offer(new Emp(scores[i][0], scores[i][1]));
        }
    
        Loop :
        while(!pq.isEmpty()){
            
            for(int i = 0 ; i < n ; i++){
                // 인센 못받는 경우
                if(scores[i][0] > pq.peek().x && scores[i][1] > pq.peek().y) {
                    // 못받는게 원호인 경우
                    if(pq.peek().x == scores[0][0] && pq.peek().y == scores[0][1]){
                        return -1;
                    }
                    // 등수 반영 x
                    pq.poll();
                    continue Loop;
                }
                
                
            }
            // 원호차례
            if(pq.peek().x == scores[0][0] && pq.peek().y == scores[0][1]){
                break;
            }
            // 원호와 동점자인 경우
            if(pq.peek().x+pq.peek().y == scores[0][0]+scores[0][1]){
                answer--;
            }
            pq.poll();
            answer++;
        }
        // 등수
        return answer;
    }
}
```