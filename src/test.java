public class test {

   public static void main(String args[]) {
      String str1 = "a";
	  String str2 = "a";
      String str3 = "b";

      int result = str1.compareTo( str2 );
      System.out.println(result);
	  
      result = str2.compareTo( str3 );
      System.out.println(result);
	 
      result = str3.compareTo( str1 );
      System.out.println(result);
   }
}