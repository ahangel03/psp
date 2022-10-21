#include <stdio.h>
#include <stdlib.h>

#include <unistd.h>
#include <sys/wait.h> 

void main(){

     int fd[2]; 
     char buffer[30];
     pid_t pid;
     int dni;
     char letra[] = "TRWAGMYFPDXBNJZSQVHLCKE";
     int valor;
               
     

     
    
     // Creamos el pipe
     pipe(fd); 
     
     //Se crea un proceso hijo
     pid = fork();

     if (pid==0)
     
     {
              
                printf("Introduzca el numero de tu dni:\n");
                close(fd[0]); // Cierra el descriptor de escritura
                scanf("%d",&dni);
                write(fd[1], &dni, 10);
                   
     
     }
     
     else
     
     {
      
                close(fd[1]); // Cierra el descriptor de lectura
	        read(fd[0],buffer,sizeof(buffer));

		valor=buffer;
	       valor%=23;
	        printf("la letra del NIF es:%c",letra[valor]);
               	        wait(NULL);
                                 

     }
     
        
}
