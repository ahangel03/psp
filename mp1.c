#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

/* Programa genera proceso HUERFANO */
/*Cuando el proceso padre finaliza antes que el proceso hijo, el proceso hijo se convierte en un proceso huérfano. Estos procesos huérfanos son "recogidos" por el proceso init o (demonio systemd)*/

int main(void) {
    pid_t pid , pid_hijo;
    pid=fork();
    if(pid==0){
    printf("Angel Hernandez");
    }
    else {
    pid_hijo=wait(NULL);
    printf("\nId del padre  %d \n", getppid());
     printf("Id del hijo  %d \n", getpid());
    }
    
}
