import java.util.ArrayList;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String s = "";
    System.out.print("\nWould you like to compute the continued fraction of sqrt(n)? (yes/no): ");
    s = scan.next();
    while(s.equals("yes")){
    System.out.print("\nPlease enter a non-square natural number: ");
      
    bigFraction(scan.nextInt());
      
    System.out.print("\n\nWould you like to compute the continued fraction of sqrt(n)? (yes/no): ");
    s = scan.next();
    }
  }

  public static int Q(int x,int n,int y){
    return ((int) Math.pow(x,2)) - n*((int) Math.pow(y,2));
  }

  public static int aProgression(int a, int b){
    return (b-a)+b;
  }
  
  public static void bigFraction(int n){
    int p = Q(1,n,0);
    int q = Q(0,n,1);
    int r = Q(1,n,1);

    int next = aProgression(q,p+r);

    int[] start={p,q,r};
    ArrayList<Integer> river = new ArrayList<>();

    int p2 = 0;

//compute river of x^2-ny^2
    while(start[0]!=p2 || start[1]!=q || start[2]!=r){
      next=aProgression(q,p+r);
      

      if((next<0 && q<0) || (next>0 && q>0)){
        //p same
        river.add(r);
        q = r;
        r = next;
        
      }
        
      else{
        river.add(r);
        
        q=p;
        p=r;
        r = next;        

      }

      p2 = p;
      
    }

//print river
    System.out.print("\nthe River: ");
    for(int x: river){
        System.out.print(x+" ");
      }

    ArrayList<Integer> arr = new ArrayList<>();
    int counter = 0;
    int i = 0;

//compute continued fraction
    while(i<river.size()){
      while(i<river.size() && river.get(i)<0){
      counter++;
      i++;
    }
      arr.add(counter);
      counter = 0;
      
      while(i<river.size() && river.get(i)>0){
      counter++;
      i++;
    }
      if(counter>0)
        arr.add(counter);
      counter = 0;

    }

    arr.set(arr.size()-1, arr.get(arr.size()-1)+arr.get(0));


//pretty print    
    System.out.print("\n\n[" + arr.get(0) + "; ");
    for(int j = 1; j<arr.size()-1; j++){
       System.out.print(arr.get(j) + ", ");
    }

     System.out.print(arr.get(arr.size()-1)+", ... ]");


  }
}