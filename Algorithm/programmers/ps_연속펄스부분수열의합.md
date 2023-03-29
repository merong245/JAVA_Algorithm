문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 연속 펄스 부분수열의 합

### 문제 이해하기
1. 펄스? -> -1,1,-1... 또는 1,-1,1 ..이 반복되는 수
2. 수열이 주어졌을 때 펄스를 곱한 부분수열의 최대 값 구하기

### 문제 접근 방법
1. n이 크기 때문에 완전탐색 x
2. 부분 수열의 합을 구하기 위해 구간합 -> dp
3. 펄스로 인해 최소값이 음수인 경우 절대값으로 변경했을 때 최대가 가능하므로 추가고려
4. 양수의 구간 합이 음수가 되는 경우 새롭게 부분 수열을 시작하는 것이 나음. 반대도 동일
5. 최대 최소를 갱신
6. 최대값과 최솟값의 절대값중 큰 것 출력

### 구현 배경 지식
1. dp
2. 구간합


### 접근 방법을 적용한 코드
```java
import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int len = sequence.length;
        // dp[0] 양수의 구간합
        // dp[1] 음수의 구간합
        long[][] dp = new long[2][len];
        
        
        dp[0][0] = sequence[0] > 0 ? sequence[0] : 0;
        dp[1][0] = sequence[0] < 0 ? sequence[0] : 0;
        long max = Math.max(dp[0][0],dp[1][0]);
        long min =  Math.min(dp[0][0],dp[1][0]);
        
        for(int i =1; i < len;i++){
            // 펄스 
            if(i%2 == 0){
                if(dp[0][i-1] + sequence[i] > 0)
                    dp[0][i] = dp[0][i-1] + sequence[i];
                if(dp[1][i-1] + sequence[i] < 0)
                    dp[1][i] = dp[1][i-1] + sequence[i];
            }
            else{
                if(dp[0][i-1] - sequence[i] > 0)
                    dp[0][i] = dp[0][i-1] - sequence[i];
                if(dp[1][i-1] - sequence[i] < 0)
                    dp[1][i] = dp[1][i-1] - sequence[i];
            }
            // 양수의 구간합 중 최대
            max = Math.max(max,dp[0][i]);
            // 음수의 구간합 중 최소
            min = Math.min(min,dp[1][i]);
            
        }
        
        // 최대와 최소를 reverse 시킨 값 중 최대인 값
        return Math.max(max, -min);
    }
}
```