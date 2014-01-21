package lcs;

import java.util.Set;
import java.util.TreeSet;

public class AllLCS {

	//Test case 1
//	private static String x = "ABCD";
//	private static String y = "ADDC";
	
	//Test case 2
//	private static String x = "CBADABDCAACD";
//	private static String y = "BACDACDBABAC";
    
	//MidTerm case 1
//	private static String x = "ACBDBCDACBDA";
//	private static String y = "DABADCBCBACD";	
	//MidTerm case 2
//	private static String x = "DCCCABDAABDC";
//	private static String y = "ADCDABACABBD";	
	//MidTerm case 3
//	private static String x = "BADCCBABCDBD";
//	private static String y = "CBCADABCDADB";	
	//MidTerm case 4
//	private static String x = "AACADBCDADCB";
//	private static String y = "DCACDCBBDBAD";
	
	//Plus
	private static String x = "CBDAACBDBCDA";
	private static String y = "BABAACDDBBCB";	
	
	private static int[][] len =  null;
	private static int[][] type = null;
	private static Set<String> outputSet = new TreeSet<String>();
	
	public static void main (String[] args) {

	    len = new int[x.length()+1][y.length()+1];
	    type = new int[x.length()+1][y.length()+1];
	    dpMatrix(x,y);

	    //backtrack all possible LCS，加入到outputSet
	    backTrack(x, y, x.length(), y.length(), new StringBuffer(), new StringBuffer(), new StringBuffer());
	    
	    //開始列印結果
	    System.out.println("--- Output ---");
	    System.out.println("Max. length = " + len[x.length()][y.length()]);
	    int i =0;
	    for(String s: outputSet){
	    	System.out.println( ++i + ".  " + s);
	    }
	    
	}

	/**
	 * 以Dynamic Programming方式建立matrix，並同時紀錄兩個資訊：LCS長度、擁有最大長度的矩陣欄位
	 * @param s1 第一個字串
	 * @param s2 第二個字串
	 */
	private static void dpMatrix(String s1, String s2) {
	    
	    for (int i = 1; i <=s1.length(); i++) {	        
	    	for (int j = 1; j <=s2.length(); j++) {
	    		
	    		if(i==0 || j==0){
	    			len[i][j] = 0 ;
	    		}
	    		
	            if (s1.charAt(i-1) == s2.charAt(j-1)) {	                
	            	
	            	len[i][j] = len[i-1][j-1] + 1;	            	
	                type[i][j] = 1;
	                
	                if(len[i][j] == len[i-1][j] && len[i][j] == len[i][j-1]){
		                type[i][j] = 5;
	                }else if(len[i][j] == len[i][j-1]){
	                	type[i][j] = 6;
	                }else if(len[i][j] == len[i-1][j]){
	                	type[i][j] = 7;
	                }
	                
	            }else{
	            		            		
	    	            if(len[i-1][j] > len[i][j-1]){	    	                
	    	            	len[i][j] = len[i-1][j];
	    	            	type[i][j] = 2;
	    	            	
	    	            }else if(len[i-1][j] < len[i][j-1]){	    	            	
	    	            	len[i][j] = len[i][j-1];
	    	            	type[i][j] = 3;	    	            
	    	            
	    	            }else if(len[i-1][j] == len[i][j-1]){
		            		//兩者相等的話
	    	            	len[i][j] = Math.max(len[i-1][j], len[i][j-1]);
	    	            	type[i][j] = 4;	            	
	    	            }
	            	
	            }

	        }// End of for
	    	
	    }// End of for
	    
	}	

	/**
	 * 從參數座標(i,j)開始backtracking，找出LCS，並同時紀錄在原本兩個字串的對應位置
	 * @param s1 第一個字串
	 * @param s2 第二個字串
	 * @param i 矩陣中行座標
	 * @param j 矩陣中列座標
	 * @param 紀錄LCS字串的物件
	 * @param 紀錄s1 LCS位置物件
	 * @param 紀錄s2 LCS位置物件
	 */
	private static void backTrack(String s1, String s2, int i, int j, StringBuffer lcs, StringBuffer s1Location, StringBuffer s2Location){
				
		   if (i == 0 || j == 0) {
			   outputSet.add(lcs.toString() + ", in X: " + s1Location.toString() + ",in Y: " + s2Location.toString());
			   	return;	   	
		    }
		   
		   switch(type[i][j]){
		   
			   case 1:			
				   backTrack(s1, s2, i-1, j-1,  
						   new StringBuffer().append(s1.charAt(i-1) + lcs.toString()),
						   new StringBuffer().append(i + " " + s1Location.toString()),
						   new StringBuffer().append(j + " " + s2Location.toString()));					   
				   break;
			   case 2:
				   backTrack(s1, s2, i-1, j, lcs, s1Location, s2Location);
				   break;
			   case 3:
				   backTrack(s1, s2, i, j-1, lcs, s1Location, s2Location);
				   break;
			   case 4:
				   backTrack(s1, s2, i-1, j, lcs, s1Location, s2Location);
				   backTrack(s1, s2, i, j-1, lcs, s1Location, s2Location); 			   
				   break;
			   case 5:
				   backTrack(s1, s2, i-1, j, lcs, s1Location, s2Location);
				   backTrack(s1, s2, i, j-1, lcs, s1Location, s2Location);
				   backTrack(s1, s2, i-1, j-1,  
						   new StringBuffer().append(s1.charAt(i-1) + lcs.toString()),
						   new StringBuffer().append(i + " " + s1Location.toString()),
						   new StringBuffer().append(j + " " + s2Location.toString()));		
				   break;
			   case 6:
				   backTrack(s1, s2, i, j-1, lcs, s1Location, s2Location);
				   backTrack(s1, s2, i-1, j-1,  
						   new StringBuffer().append(s1.charAt(i-1) + lcs.toString()),
						   new StringBuffer().append(i + " " + s1Location.toString()),
						   new StringBuffer().append(j + " " + s2Location.toString()));			   
				   break;
			   case 7:
				   backTrack(s1, s2, i-1, j, lcs, s1Location, s2Location);
				   backTrack(s1, s2, i-1, j-1,  
						   new StringBuffer().append(s1.charAt(i-1) + lcs.toString()),
						   new StringBuffer().append(i + " " + s1Location.toString()),
						   new StringBuffer().append(j + " " + s2Location.toString()));	
				   break;				   
		   
		   }
			
		}
	
}
