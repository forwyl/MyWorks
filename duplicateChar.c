#include <stdio.h>

void duplicateChar(){

    char input[80];
    int i = 0;
    puts("請輸入英文或數字的字串...");
    gets(input);
    if(input[i]=='\0'){
        printf("此為空字串");
        return;
    }

    int asciiBoolean[128] ={0};
    int codeNum = input[i] - 0;

    while(codeNum > 0){
        if(asciiBoolean[codeNum]==1){
            printf("出現重複字元 %c, 程式中止\n", input[i]);
            return;
        }
        asciiBoolean[codeNum]=1;
        codeNum = input[++i] - 0;
    }

    printf("沒有出現重複字元");
}

int main(){
    duplicateChar();
    return 0;
}
