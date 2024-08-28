import java.io.IOException;
import java.util.*;

public class Knapsack{
    public static void main(String...args){
    
    int i=0, j=0, max_qty, m, n;
    float sum=0, max;
    int a[][]= new int[5][5];
    
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter number of items:");
    n=sc.nextInt();
    
    System.out.println("Enter weights:");
    for(i=0; i<n; i++){
        a[i][0]=sc.nextInt();
    }
    
    System.out.println("Enter profits:");
     for(i=0; i<n; i++){
        a[1][i]=sc.nextInt();
    }
    
    System.out.println("Enter large volume of Knapsack:");
    max_qty=sc.nextInt();
    
    m=max_qty;
    while(m>=0){
    max=0;
        for(i=0; i<n; i++){
            if( ((float)a[1][i]/(float) a[0][i]) > max){
            //if( (a[1][i])/( a[0][i])){
                max= (((float) a[1][i])/((float) a[0][i]));
                //m= ((a[1][i])/(a[0][i]));
                j=i;
            }
            
        }
        
        if(a[0][j]>=m){
            System.out.println("Quantity of item "+(j+1)+" is: "+m);
            sum+=m*max;
            m=-1;
        }
        else{
            System.out.println("Quantity of item "+(j+1)+" is: "+ a[0][j]);
            sum+= (float)a[1][j];
            m-=a[0][j];
            a[0][j]=0;
        }
        }
    
    
    System.out.println("Profit is:"+sum);
    sc.close();
  }
}  
                
    
