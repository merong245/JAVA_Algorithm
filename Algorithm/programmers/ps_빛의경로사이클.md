문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 빛의 경로 사이클


### 문제 이해하기
1. 각 칸마다 S, L, 또는 R가 써져 있는 격자가 있습니다. 당신은 이 격자에서 빛을 쏘고자 합니다. 이 격자의 각 칸에는 다음과 같은 특이한 성질이 있습니다.
2. 빛이 "S"가 써진 칸에 도달한 경우, 직진합니다.
3. 빛이 "L"이 써진 칸에 도달한 경우, 좌회전을 합니다.
4. 빛이 "R"이 써진 칸에 도달한 경우, 우회전을 합니다.
5. 빛이 격자의 끝을 넘어갈 경우, 반대쪽 끝으로 다시 돌아옵니다. 예를 들어, 빛이 1행에서 행이 줄어드는 방향으로 이동할 경우, 같은 열의 반대쪽 끝 행으로 다시 돌아옵니다.
6. 당신은 이 격자 내에서 빛이 이동할 수 있는 경로 사이클이 몇 개 있고, 각 사이클의 길이가 얼마인지 알고 싶습니다. 경로 사이클이란, 빛이 이동하는 순환 경로를 의미합니다.
7. 빛의 경로 사이클의 모든 길이들을 배열에 담아 오름차순으로 정렬

### 문제 접근 방법
1. 길이 N <= 500이고 4방탐색을 하기 때문에 O(500^2 * 4)로 1초내에 해결가능
2. bfs를 이용하여 탐색
3. 비트마스킹을 통해 이미 방문한 경로인 경우 사이클이 존재하는 경우이다.
4. 동일한 사이클의 경우 추가하지 않으므로 카운팅하지 않음

### 구현 배경 지식
1. bfs

### 접근 방법을 적용한 코드
```java
import java.util.*;

class Solution {
    int n,m;
    int[] dxs = {1,0,-1,0};
    int[] dys = {0,-1,0,1};
    String[] grid;
    
    public int[] solution(String[] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length();
        return simulate();
    
    }
    
    public int[] simulate(){
        List<Integer> answer = new LinkedList<>();
        
        int[][] visited = new int[n][m];
        for(int i = 0 ; i < n; i++){
            for(int j =0; j< m; j++){
                for(int k = 0; k< 4; k++){

                    int count = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i,j,k});

                    if((visited[i][j] & 1 << k) > 0) continue;

                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int x = cur[0] , y = cur[1] , d = cur[2];


                        if(grid[x].charAt(y) == 'L'){
                            d -= 1;
                        }
                        else if(grid[x].charAt(y) == 'R'){
                            d += 1;
                        }

                        d = (4+d) % 4;
                        int nx = (n+cur[0] + dxs[d])%n;
                        int ny = (m+cur[1] + dys[d])%m;

                        if((visited[nx][ny] & 1<<d) > 0) continue;
                        visited[nx][ny] |= 1 << d;

                        q.offer(new int[]{nx,ny,d});
                        count++;

                    }
                    if(count > 0)
                        answer.add(count);
                    
                }
            }
        }
        
        Collections.sort(answer);
        return answer.stream().mapToInt(x->x).toArray();
    }
}
```