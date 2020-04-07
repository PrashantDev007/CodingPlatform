class HelloWorld 
{ 
 
    public static void main(String args[]) 
    { 
        System.out.println("Hello, World"); 
    
 int i, j,n=5; 
  
        // outer loop to handle number of rows 
        //  n in this case 
        for(i=0; i<n; i++) 
        { 
  
            for(j=2*(n-i); j>=0; j--) 
            { 
                // printing spaces 
                System.out.print(" "); 
            } 

            for(j=0; j<=i; j++) 
            { 
                // printing stars 
                System.out.print("* "); 
            } 
              
       
            System.out.println();











}









} 
} 