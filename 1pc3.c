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
  int numero1;
  int numero2;
  numero1 = rand () % 50;
  numero2 = rand () % 50;
  int suma;
  int resta;
  int division;
  int multiplicacion;

  pid = fork ();
  if (pid == 0)
    {

      close (fd[1]);
      printf ("Numeros leidos: \n");

      read (fd[0], &numero1, sizeof (numero1));
      read (fd[0], &numero2, sizeof (numero2));
      printf ("numero 1 = %d\n", numero1);
      printf ("numero 2 = %d\n", numero2);

      suma = numero1 + numero2;
      printf ("Suma = %d\n", suma);
      resta = numero1 - numero2;
      printf ("Resta = %d\n", resta);
      division = numero1 / numero2;
      printf ("Division = %d\n", division);
      multiplicacion = numero1 * numero2;
      printf ("Multiplicacion = %d\n", multiplicacion);



    }



  else

    {
      close (fd[0]);		// Cierra el descriptor de lectura
      printf ("Generando 2 numeros aleatorios:\n");
      printf ("numero 1 = %d\n", numero1);
      printf ("numero 2 = %d\n", numero2);

      write (fd[1], &numero1, sizeof (numero1));
      write (fd[1], &numero2, sizeof (numero2));

      wait (NULL);
    }


}
