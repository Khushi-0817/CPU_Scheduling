import java.util.Scanner; 
 
class SchedulingAlgorithms { 
    public static void roundRobin(int n, int bt[], int quantum) { 
        int wt[] = new int[n]; 
        int tat[] = new int[n]; 
        int rem_bt[] = new int[n]; 
 
        for (int i = 0; i < n; i++) { 
            rem_bt[i] = bt[i]; 
        } 
 
        int t = 0;  
 
        while (true) { 
            boolean done = true; 
 
            for (int i = 0; i < n; i++) { 
                if (rem_bt[i] > 0) { 
                    done = false; 
 
                    if (rem_bt[i] > quantum) { 
                        t += quantum; 
                        rem_bt[i] -= quantum; 
                    } else { 
                        t += rem_bt[i]; 
                        wt[i] = t - bt[i]; 
                        rem_bt[i] = 0; 
                    } 
                } 
            } 
 
            if (done) { 
                break; 
            } 
        } 
 
        for (int i = 0; i < n; i++) { 
            tat[i] = bt[i] + wt[i]; 
        } 
 
        displayResults(n, bt, wt, tat); 
    } 
 
    public static void firstComeFirstServe(int n, int bt[]) { 
        int wt[] = new int[n]; 
        int tat[] = new int[n]; 
 
        wt[0] = 0;  
        for (int i = 1; i < n; i++) { 
            wt[i] = bt[i - 1] + wt[i - 1]; 
        } 
 
        for (int i = 0; i < n; i++) { 
            tat[i] = bt[i] + wt[i]; 
        } 
 
        displayResults(n, bt, wt, tat); 
    } 
 
    public static void shortestJobFirst(int n, int bt[]) { 
        int wt[] = new int[n]; 
        int tat[] = new int[n]; 
        int pid[] = new int[n]; 
 
        for (int i = 0; i < n; i++) { 
            pid[i] = i + 1; 
        } 
 
        for (int i = 0; i < n - 1; i++) { 
            for (int j = 0; j < n - i - 1; j++) { 
                if (bt[j] > bt[j + 1]) { 
                    int temp = bt[j]; 
                    bt[j] = bt[j + 1]; 
                    bt[j + 1] = temp; 
 
                    temp = pid[j]; 
                    pid[j] = pid[j + 1]; 
                    pid[j + 1] = temp; 
                } 
            } 
        } 
 
        wt[0] = 0; 
 
        for (int i = 1; i < n; i++) { 
            wt[i] = bt[i - 1] + wt[i - 1]; 
        } 
 
        for (int i = 0; i < n; i++) { 
            tat[i] = bt[i] + wt[i]; 
        } 
 
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time"); 
        for (int i = 0; i < n; i++) { 
            System.out.println(pid[i] + "\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]); 
        } 
 
        int total_wt = 0, total_tat = 0; 
        for (int i = 0; i < n; i++) { 
            total_wt += wt[i]; 
            total_tat += tat[i]; 
        } 
 
        System.out.println("\nAverage waiting time: " + (float) total_wt / n); 
        System.out.println("Average turnaround time: " + (float) total_tat / n); 
    } 
 
    public static void displayResults(int n, int bt[], int wt[], int tat[]) { 
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time"); 
        for (int i = 0; i < n; i++) { 
            System.out.println((i + 1) + "\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]); 
        } 
 
        int total_wt = 0, total_tat = 0; 
        for (int i = 0; i < n; i++) { 
            total_wt += wt[i]; 
            total_tat += tat[i]; 
        } 
 
        System.out.println("\nAverage waiting time: " + (float) total_wt / n); 
        System.out.println("Average turnaround time: " + (float) total_tat / n); 
    } 
} 
 
public class Main { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
 
        System.out.println("Select a scheduling algorithm:"); 
        System.out.println("1. Round Robin"); 
        System.out.println("2. First Come First Serve (FCFS)"); 
        System.out.println("3. Shortest Job First (SJF)"); 
        System.out.print("Enter your choice (1/2/3): "); 
        int choice = sc.nextInt(); 
 
        System.out.println("Enter the number of processes:"); 
        int n = sc.nextInt(); 
 
        int burstTime[] = new int[n]; 
 
        System.out.println("Enter burst times for each process:"); 
        for (int i = 0; i < n; i++) { 
            burstTime[i] = sc.nextInt(); 
        } 
 
        switch (choice) { 
            case 1: 
                System.out.println("Enter time quantum for Round Robin:"); 
                int quantum = sc.nextInt(); 
                SchedulingAlgorithms.roundRobin(n, burstTime, quantum); 
                break; 
            case 2: 
                SchedulingAlgorithms.firstComeFirstServe(n, burstTime); 
                break; 
            case 3: 
                SchedulingAlgorithms.shortestJobFirst(n, burstTime); 
                break; 
            default: 
                System.out.println("Invalid choice!"); 
                break; 
        } 
 
        sc.close(); 
    } 
} 
