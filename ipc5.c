#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h> 

void main(){

     int fd1[2]; 
     int fd2[2]; 
     char buffer[30];
     pid_t pid;
     int dni;
     char letra[] = "TRWAGMYFPDXBNJZSQVHLCKE";
     int valor;
               
     

     
    
     // Creamos el pipe
     pipe(fd1); 
     pipe(fd2);
     //Se crea un proceso hijo
     pid = fork();

     if (pid==0)
     
     { 	close(fd1[0]); 
               close(fd2[1]);
                printf("Introduzca el numero de tu dni:\n");
               // Cierra el descriptor de escritura
               
                scanf("%d",&dni);
                sprintf(buffer,"%d",dni);
                write(fd1[1], buffer, sizeof(buffer));
                read(fd2[0],buffer,sizeof(buffer));
                  printf("La letra del nif es :%d",letra[valor]);
     
     }
     
     else
     
     {
      
                close(fd1[1]);
                close(fd2[0]); // Cierra el descriptor de lectura
	        read(fd1[0],buffer,sizeof(buffer));//el padre lee el pipe 1
	        printf("%s",buffer);
		
		valor=atoi(buffer);
	       valor%=23;
	        printf("la letra del NIF es:%c",letra[valor]);
	        sprintf(buffer,"%d",dni);
	        write(fd2[1],buffer,sizeof(buffer));
               	        wait(NULL);
                                 

     }
     
        
}
