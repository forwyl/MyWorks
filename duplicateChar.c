#include <stdio.h>

void duplicateChar(){

    char input[80];
    int i = 0;
    puts("�п�J�^��μƦr���r��...");
    gets(input);
    if(input[i]=='\0'){
        printf("�����Ŧr��");
        return;
    }

    int asciiBoolean[128] ={0};
    int codeNum = input[i] - 0;

    while(codeNum > 0){
        if(asciiBoolean[codeNum]==1){
            printf("�X�{���Ʀr�� %c, �{������\n", input[i]);
            return;
        }
        asciiBoolean[codeNum]=1;
        codeNum = input[++i] - 0;
    }

    printf("�S���X�{���Ʀr��");
}

int main(){
    duplicateChar();
    return 0;
}
