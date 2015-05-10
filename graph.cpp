#include "edge.cpp"
#include<math.h>
#include<stdlib.h>
const int N=10;
int** arr;
int main(){
edge e;  arr = new int*[N];
for(int i=0;i<N;i++)arr[i]=new int[N];
for(int i=0;i<N;i++)
    for(int i1=0;i1<N;i1++){
           if(i!=i1)arr[i][i1]=rand()%100;
           else arr[i][i1]=0;
    }
}
