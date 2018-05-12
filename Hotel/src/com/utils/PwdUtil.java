package com.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class PwdUtil {

	public static int getPwdLevel(String password) {
         int len = password.length() ;
         int countUpper = 0 ;
         int countLower = 0 ;
         int countNum = 0 ;
         int countSym = 0 ;
         
         int score = 0 ;
         char temp ;
         for(int i = 0 ; i < len ; i++){
             temp = password.charAt(i) ;
             if((temp <= 'Z') && (temp >= 'A')){
                 //大写字母个数
                 countUpper++ ;
             }else if((temp <= 'z') && (temp >= 'a')){
                 //小写字母个数
                 countLower++ ;
             }else if((temp <= '9') && (temp >= '0')){
                 //数字个数
                 countNum++ ;
             }else if(((temp >= 0x21) && (temp <= 0x2F)) || 
                      ((temp >= 0x3A) && (temp <= 0x40)) ||
                      ((temp >= 0x7B) && (temp <= 0x7E))){
                 //符号个数
                 countSym++ ;
             }                    
         }        
         
         //计算根据长度判断的得分
         if(len <= 4){
             score += 5 ;
         }else if((len <= 7) && (len > 4)){
             score += 10 ;
         }else{
             score += 25 ;
         }
         //计算根据字母情况判断得分
         if(((countUpper != 0) && (countLower == 0)) ||
            ((countUpper == 0) && (countLower != 0))){
             score += 10 ;
         }else if((countUpper != 0) && (countLower != 0)){
             score += 20 ;
         }
         //计算根据数字个数判断得分
         if(countNum == 1){
             score += 10 ;
         }else if(countNum > 1){
             score += 20 ;
         }
         //计算根据符号个数判断的得分
         if(countSym == 1){
             score += 10 ;
         }else if(countSym > 1){
             score += 25 ;
         }
         //计算奖励的判断得分
         if((countUpper != 0) && (countLower != 0) && 
             (countNum != 0) && (countSym != 0)){
             score += 5 ;
         }else if(((countUpper + countLower) != 0) && 
                 (countNum != 0) && (countSym != 0)){
             score += 3 ;
         }else if(((countUpper + countLower) != 0) && 
                 (countNum != 0) && (countSym == 0)){
             score += 2 ;
         }    
         //根据得分确定最后的评级，并返回
         return score;
        /* if(score >= 90){
             return SecurityLevel.VERY_SECURE ;
         }else if(score >= 80){
             return SecurityLevel.SECURE ;
         }else if(score >= 70){
             return SecurityLevel.VERY_STRONG ;
         }else if(score >= 60){
             return SecurityLevel.STRONG ;
         }else if(score >= 50){
             return SecurityLevel.AVERAGE ;
         }else if(score >= 25){
             return SecurityLevel.WEAK ;
         }else{
             return SecurityLevel.VERY_WEAK ;
         }*/
 
     }
	public static void setPwdLevel(Composite composite,int score,int level,int red,int green,int blue) {
		if(score>=level) {
			composite.setBackground(org.eclipse.wb.swt.SWTResourceManager.getColor(red,green,blue));
		}else {
			composite.setBackground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
	}
}
