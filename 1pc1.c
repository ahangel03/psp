#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
void
main ()
{

  int fd[2];
  pid_t pid;

  // Creamos el pipe
  pipe (fd);
 time_t hora;
char *fecha ;
time(&hora);
fecha = ctime(&hora) ;
  pid = fork ();

  if (pid == 0)

    {
      close (fd[1]);		// Cierra el descriptor de lectura
      printf ("El hijo lee en el PIPE \n");
      read (fd[0], &fecha, sizeof (fecha));
     printf ("\t Mensaje leido del pipe: %s \n", fecha);
    }

  else

    {
      close (fd[0]);	
      printf ("El padre escribe el PIPE\n");
      write (fd[1], &fecha, sizeof (fecha));
     
      wait (NULL);
    }


}

