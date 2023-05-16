문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 귤고르기


### 문제 이해하기
1. k개의 귤을 선정하려한다.
2. 귤의 종류가 가장 적어야함!

### 문제 접근 방법
1. N이 크다. 완전탐색 불가
2. 개수 카운팅 Map 이용 
3. 종류가 적으려면 작은 개수의 종류 먼저 제거해야한다 -> 우선순위 큐

### 구현 배경 지식
1. 우선순위 큐
2. 해시 맵

### 접근 방법을 적용한 코드
```java
import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int n = tangerine.length;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        // 개수 카운팅해서 넣어줌
        for(int i =0 ; i < n; i++){
            int temp = tangerine[i];
            if(map.containsKey(temp)){
                map.put(temp,map.get(temp)+1);
            }
            else
                map.put(temp,1);
        }
        
        // 개수가 적은게 우선순위 높음 -> 종류를 많이 줄일 수 있음
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            pq.offer(new int[]{e.getValue(),e.getKey()});
        }
        
        // k 이하 될 때 까지 반복
        while(true){
            if(n > k)
                n-=pq.poll()[0];
            // 딱 k개 인 경우
            else if(n == k)
                return pq.size();
            // k 보다 작은 경우 종류 1개 추가
            else 
                return pq.size()+1;
        }
    }
}
```
