#include <stdio.h>
#include <string.h>

void reverseCString(){

    char input[80];
    int beginIndex = 0;
    int endIndex = 0;
    char temp;

    puts("請輸入字串：");
    gets(input);
    printf("輸入字串為%s\n", input);

    while(input[beginIndex++]!= '\0')

    endIndex = beginIndex - 1;

    beginIndex = 0;
    while(beginIndex < endIndex){
        temp = input[beginIndex];
        input[beginIndex] = input[endIndex];
        input[endIndex] = temp;
        beginIndex++;
        endIndex--;
    }

    printf("反轉後的字串為%s\n", input);
}

int main(){
    reverseCString();
    return 0;
}
