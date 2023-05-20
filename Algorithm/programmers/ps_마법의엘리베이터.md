문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 마법의 엘리베이터


### 문제 이해하기
1. 민수는 마법의 엘리베이터로 현재층에서 0층으로 이동하려한다.
2. 마법의 엘리베이터는 -1, +1, -10, +10, -100, ...으로 절댓값이 10의 제곱수만큼 이동한다.
3. 현재 층수가 주어졌을때 최소 이동횟수로 0층에 도착할때 이동횟수를 구해라.

### 문제 접근 방법
1. 시작점에서 -1, +1, -10, +10, ... 등 10의 제곱수 만큼 이동해서 도착점으로 가야하므로 BFS를 고려
2. 모든 -100,000,000 ~ 100,000,000을 전부 계산하는 것은 시간초과가 발생한다.
3. 1의 자리를 고려해보자.
4. 16층이라면 -10층 1번, -1층을 6번 눌러서 총 7번에 이동 가능하다.
5. 또한 +1층 4번 -10층 2번으로 총 6번만에도 이동가능하다.
6. 다음 10의 제곱수에서 처리가능한경우와 현재 제곱수에서 처리가능한지 확인하고 이동횟수가 적은 우선순위를 갖는 우선순위 큐를 생성한다.
7. 이를 위해 MOD 연산과 다음으로 이동한 층수를 고려하여 우선순위 큐를 이용한 BFS를 이용한다.


### 구현 배경 지식
1. 우선순위 큐
2. 모듈러 연산
3. 수학
4. BFS

### 접근 방법을 적용한 코드
```java
import java.util.*;

class Node{
    int x, cnt;
    Node(int x, int cnt){
        this.x = x;
        this.cnt = cnt;
    }
}

class Solution {
    public int solution(int storey) {
        int answer = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            return Integer.compare(a.cnt,b.cnt);
        });
        pq.offer(new Node(storey,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.x == 0){
                answer = cur.cnt;
                break;
            }
            
            // 16 -> 10 6 -> +10*1 +1*6 -> 7
            pq.offer(new Node(cur.x/10,cur.cnt+cur.x%10));
            // 16 -> 20 4 -> +10*2 +1*4 -> 6
            pq.offer(new Node(cur.x/10+1,cur.cnt+10-cur.x%10));
        }
        
        return answer;
    }
}
```
