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

	    //backtrack all possible LCS�A�[�J��outputSet
	    backTrack(x, y, x.length(), y.length(), new StringBuffer(), new StringBuffer(), new StringBuffer());
	    
	    //�}�l�C�L���G
	    System.out.println("--- Output ---");
	    System.out.println("Max. length = " + len[x.length()][y.length()]);
	    int i =0;
	    for(String s: outputSet){
	    	System.out.println( ++i + ".  " + s);
	    }
	    
	}

	/**
	 * �HDynamic Programming�覡�إ�matrix�A�æP�ɬ�����Ӹ�T�GLCS���סB�֦��̤j���ת��x�}���
	 * @param s1 �Ĥ@�Ӧr��
	 * @param s2 �ĤG�Ӧr��
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
		            		//��̬۵�����
	    	            	len[i][j] = Math.max(len[i-1][j], len[i][j-1]);
	    	            	type[i][j] = 4;	            	
	    	            }
	            	
	            }

	        }// End of for
	    	
	    }// End of for
	    
	}	

	/**
	 * �q�ѼƮy��(i,j)�}�lbacktracking�A��XLCS�A�æP�ɬ����b�쥻��Ӧr�ꪺ������m
	 * @param s1 �Ĥ@�Ӧr��
	 * @param s2 �ĤG�Ӧr��
	 * @param i �x�}����y��
	 * @param j �x�}���C�y��
	 * @param ����LCS�r�ꪺ����
	 * @param ����s1 LCS��m����
	 * @param ����s2 LCS��m����
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
