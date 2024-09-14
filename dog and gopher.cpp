#include<stdio.h>
#include<math.h>

int main(){
    int n;
    double gopher_x,gopher_y,dog_x,dog_y;
    while(scanf("%d%lf%lf%lf%lf",&n,&gopher_x,&gopher_y,&dog_x,&dog_y)==5){
        bool escape=false;
        double x,y,tx,ty;
        int i,j;
        bool escape=false;
		double x,y,tx,ty;
		int i,j;
		for(i=1;i<=n;i++){
			scanf("%lf%lf",&x,&y);
			if(!escape&&sqrt((gopher_x-x)*(gopher_x-x)+(gopher_y-y)*(gopher_y-y))*2<=sqrt((dog_x-x)*(dog_x-x)+(dog_y-y)*(dog_y-y))){
				escape=true;
				tx=x;
				ty=y;
			}
		}
        if(escape)
			printf("The gopher can escape through the hole at (%.3lf,%.3lf).\n",tx,ty);
		else
			puts("The gopher cannot escape.");

	}
	return 0;
}
