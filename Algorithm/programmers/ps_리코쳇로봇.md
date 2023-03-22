문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 리코쳇로봇


### 문제 이해하기
1. R에서 출발하여 4방으로 로봇이 미끄러져서 이동
2. D를 만나거나 벽을 만나면 멈춤
3. G에 정지할 때 최소 이동 횟수 구하기

### 문제 접근 방법
1. bfs로 4방 탐색
2. 가능한 거리까지 직진
3. 정지했을 때 최소거리 갱신

### 구현 배경 지식
1. bfs


### 접근 방법을 적용한 코드
```java
import java.util.*;

class Solution {
    static int[] dxs = {0,1,0,-1};
    static int[] dys = {1,0,-1,0};
    public int solution(String[] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length();
        for(int i =0; i< n; i++){
            for(int j= 0; j<m; j++){
                if(board[i].charAt(j) == 'R'){
                    return bfs(board,i,j,n,m);
                }
            }
            
        }
        return answer;
    }
    public int bfs(String[] board, int sx, int sy, int n, int m){
        // 초기화
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[n][m];
        q.offer(new int[]{sx,sy});
        
        for(int i = 0; i < n ; i++){
            Arrays.fill(visited[i],0);
        }
        visited[sx][sy] = 1;
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            
            for(int i = 0 ; i < 4; i++){
                int nx = -1, ny= -1;
                for(int j=0; j< Math.max(n,m);j++){
                    nx = curr[0]+dxs[i]*j;
                    ny = curr[1]+dys[i]*j;
                    if(0<= nx && nx <n && 0<= ny && ny<m){
                        if(board[nx].charAt(ny)=='D'){
                            // 현재 위치가 D라면 한 칸 전으로 이동
                            nx -= dxs[i];
                            ny -= dys[i];
                            break;
                        } 
                    } 
                    else{
                        // 초과 범위라면 한 칸 전에서 탈출
                        nx -= dxs[i];
                        ny -= dys[i];
                        break;
                    }           
                }
                // 이전에 방문했다면 재탐색 ㄴㄴ
                if(visited[nx][ny] == 0)
                {
                    // 목표 도착한 경우
                    if(board[nx].charAt(ny) == 'G'){
                        return visited[curr[0]][curr[1]];
                    }
                    // 다음 이동
                    q.offer(new int[]{nx,ny});
                    visited[nx][ny] = visited[curr[0]][curr[1]]+1;
                }
            }
        }
        // 이동 불가한 경우
        return -1;
    }
}
```

