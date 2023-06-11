문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 풍선 터트리기

### 문제 이해하기
1. 모든 풍선에는 서로 다른 숫자가 써져 있음
2. 임의의 인접한 두 풍선을 고른뒤 두 풍선 중 하나를 터뜨림
3. 터진 풍선에 의해 생긴 빈 공간은 풍선들을 중앙으로 밀착시킴
4. 작은 풍선을 터트리는 행위는 한 번만 가능
5. 이렇게 풍선을 터트릴 때 최후까지 남는 풍선을 구하고 싶음
6. 어떠한 방법으로도 최후까지 남지 않을 수 있음

### 문제 접근 방법
1. 풍선의 수 N <= 1000000으로 O(N^2)은 시간 초과난다.
2. 현재 풍선을 최후까지 살아남을 수 있는 아이디어를 떠올려보자.
3. 한쪽에 가장 작은 풍선이 있고, 반대쪽에 가장 작은 풍선이 현재 자신보다 크다면 이 풍선은 무조건 최후까지 살아남을 수 있다.
4. 가장 작은 풍선이 왼쪽 또는 오른쪽에 있는 경우를 고려하자.
5. 여기까지만 해도 O(N^2)이므로 다른 아이디어를 떠올려야한다.
6. O(N)으로 진행하는 슬라이딩 윈도우를 이용하고
7. 왼쪽과 오른쪽의 최대 최소와 값을 삭제 삽입하기 위해 TreeSet을 이용하여 O(logN)을 이용한다.
8. 총 O(NlogN)의 시간복잡도로 시간내에 해결 가능하다.

### 구현 배경 지식
1. 슬라이딩 윈도우
2. TreeSet

### 접근 방법을 적용한 코드
```java
import java.util.*;

class Solution {
    static int minValue = Integer.MAX_VALUE;
    public int solution(int[] a) {
        int answer = 0;
        // 한쪽 방향으로는 제일 작은 값 존재
        // 반대 방향은 현재 수가 제일 작아야함
        // 양끝은 항상 가능
        TreeSet<Integer> left = new TreeSet<>();
        TreeSet<Integer> right = new TreeSet<>();
        
        for(int i : a){
            minValue = Math.min(minValue, i);
            right.add(i);
        }

        // O(N^2) 시간초과.. 음.. O(N) 또는 O(NlogN)
        // TreeSet + 슬라이딩 윈도우를 이용해서 O(NlogN)
        for(int i : a){
            if(left.isEmpty()){
                right.remove(i);
                left.add(i);
                answer++;
            }
            else if(right.isEmpty()){
                answer++;
            }
            else{
                if(left.first() >= i && right.first() <=i){
                    answer++;
                }
                else if(left.first() <= i && right.first() >=i){
                    answer++;
                }
                right.remove(i);
                left.add(i);
            }
        }
        return answer;
    }
    
}
```
