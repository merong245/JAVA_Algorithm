문제 회고를 위해 알고리즘 풀이 기록을 남깁니다.

# 프로그래머스 부대복귀


### 문제 이해하기
1. 부대원들이 흩어져서 임무 수행 중
2. 연결된 두 지역간 길의 이동 시간은 1로 동일
3. 부대원들이 위치한 좌표들이 주어지고 도착지가 주어진다.
4. 또학 연결된 지역들이 주어지는데 이는 a,b라면 b,a도 성립한다.
5. 이때 복귀 불가능하다면 -1을 저장하고 가능한 최단시간을 구해라

### 문제 접근 방법
1. 최단 경로이고, 가중치가 1(양수)
2. 너비 우선 탐색과 다익스트라 알고리즘을 떠올릴 수 있다.
3. N <= 100000으로 크기 때문에 방문처리배열의 경우 메모리 초과 가능
4. 다익스트라 채택
5. 각 소스로 목적지를 조회 -> 시간초과
6. 목적지에 도달하면 바로 탈출로 개선 가능
7. 목적지가 하나인 점에서 고려해보면 목적지에서 한번의 다익스트라를 통해 각 시작점에 대한 최단경로를 구할 수 있다


### 구현 배경 지식
1. 다익스트라

### 접근 방법을 적용한 코드
```java
import java.util.*;

class Node{
    int x, cost;

    Node(int x, int cost){
        this.x = x;
        this.cost =cost;
    }
}
class Solution {
    static final int INF = 100000000;
    static List<Integer>[] list;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        list = new LinkedList[n+1];
        int[] distance = new int[n+1];

        for(int i = 1 ; i <= n; i++){
            list[i] = new LinkedList<Integer>();
        }
        for(int i = 0 ; i < roads.length; i++){
            int a = roads[i][0];
            int b = roads[i][1];
            list[a].add(b);
            list[b].add(a);

        }
        Arrays.fill(distance,INF);
        dijkstra(destination,distance);

        // 각 시작점을 매번 탐색하는 것이 아니라 도착점에서 한번만 탐색해주면 된다 
        for(int i =0; i < sources.length; i++){
            answer[i] = distance[sources[i]];
            if(answer[i] == INF)
                answer[i] = -1;
        }
        return answer;
    }

    public void dijkstra(int start, int[] distance){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) ->{
            return Integer.compare(a.cost,b.cost);
        });
        distance[start] = 0;
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            // 이미 처리된 경우
            if(distance[cur.x] < cur.cost) continue;

            for(int next : list[cur.x]){
                int cost = cur.cost + 1;
                if(distance[next]> cost){
                    distance[next] = cost;
                    pq.offer(new Node(next,cost));
                }
            }


        }

    }
}
```

