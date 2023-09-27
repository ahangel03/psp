    #include <stdio.h>
    #include <stdlib.h>
    #include <sys/types.h>
    #include <unistd.h>
    #include <sys/wait.h>

    void main() {

      pid_t pid_hijo1, pid_hijo2;

      pid_hijo1 = fork();
      printf("Proceso PADRE = %d\n", getppid());
      if (pid_hijo1 == 0) {
        sleep(10);
        printf("Despierto\n");
        printf("P2: PID = %d, PPID = %d\n", getpid(), getppid());

        exit(0);
      }
      pid_hijo2 = fork();
      if (pid_hijo2 == 0) {
        printf("P3: PID = %d, PPID = %d\n", getpid(), getppid());
        exit(0);

      }

      exit(0);

    }
