#include <stdio.h>
#include <string.h>

void reverseCString(){

    char input[80];
    int beginIndex = 0;
    int endIndex = 0;
    char temp;

    puts("�п�J�r��G");
    gets(input);
    printf("��J�r�ꬰ%s\n", input);

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

    printf("����᪺�r�ꬰ%s\n", input);
}

int main(){
    reverseCString();
    return 0;
}
