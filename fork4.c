#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t p1, p2, p3, p4, p5;
    
    p1 = getpid();  // Obtener el ID del proceso actual (P1)
    
    printf("P1 (Padre): PID = %d\n", p1);
    
    p2 = fork();  // Crear el primer hijo (P2)
    
    if (p2 == 0) {
        p5 = fork();  // Crear el hijo de P2 (P5)
        
        if (p5 == 0) {
            printf("P5: PID = %d, PPID = %d\n", getpid(), getppid());
        } else {
            printf("P2: PID = %d, PPID = %d\n", getpid(), getppid());
        }
    } else {
        p3 = fork();  // Crear el segundo hijo (P3)
        
        if (p3 == 0) {
            printf("P3: PID = %d, PPID = %d\n", getpid(), getppid());
        } else {
            p4 = fork();  // Crear el tercer hijo (P4)
            
            if (p4 == 0) {
                printf("P4: PID = %d, PPID = %d\n", getpid(), getppid());
            } else {
                // Proceso padre (P1) espera a sus hijos
                wait(NULL);
                wait(NULL);
                wait(NULL);
            }
        }
    }
    
    return 0;
}