#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int compareStr(char * userString, char * stuString){
	
	int isSame = 0;

	if (strlen(userString) <= strlen(stuString)){
		isSame = 1;
		for (int i = 0; i<= strlen(userString)-1;i++){
			if (userString[i]!=stuString[i]){
				
				isSame = 0;

			}
			

		}
	}


	return isSame;
}


int main(void){

	char * userName = "David";
	char * stuName = "David Spiegel";
	printf("%s\n",userName);
	printf("%s\n",stuName);

	int val = compareStr(userName,stuName);
	printf("The compare function: %d\n",val);



}







