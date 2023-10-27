#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
void
main (){

  int fd[2];
  pid_t pid;

  // Creamos el pipe
  pipe (fd);
  int numeros[4];
  int resultado=0;
  char sumando;
  pid = fork();
  if (pid == 0)    {
  close(fd[1]); // Cierra el descriptor de escritura

    read(fd[0], &sumando, sizeof(sumando));
    for (int i = 0; i < 4; i++) {
      read(fd[0], &numeros[i], sizeof(int));
    }

    printf("Operación: %c\n", sumando);
    printf("Números leídos: ");
    for (int i = 0; i < 4; i++) {
      printf("%d ", numeros[i]);
      resultado=resultado +numeros[i];
    }
    printf("%d\n",resultado);
}

    

  else

    {
    close(fd[0]); // Cierra el descriptor de lectura

    printf("Introduce 4 números:\n");
      scanf("%d %d %d %d", &numeros[0],&numeros[1],&numeros[2],&numeros[3]);
   
        printf("introduzca un caracter\n");
           scanf("%s", &sumando);
      write (fd[1], &sumando, sizeof (sumando));
       write(fd[1], &numeros, sizeof(numeros));
        printf("%c\n",sumando);
      wait (NULL);
    }


}
