"""
author : https://github.com/daeunkwak
date : 2024-04-30
title : [PCCP 모의고사 #2] 1번 - 실습용 로봇
"""

import java.util.*;

class Solution {
    public int[] solution(String command) {
        int[] answer = new int[2];

        char[] direc = {'u', 'r', 'd', 'l'};
        int idx = 0;
        int x = 0;
        int y = 0;

        for(int i = 0; i < command.length(); i++){
            switch(command.charAt(i)){
                case 'R' :
                    if(idx == 3){
                        idx = 0;
                    } else{
                        idx += 1;
                    }
                    break;
                case 'L' :
                    if(idx == 0){
                        idx = 3;
                    } else{
                        idx -= 1;
                    }
                    break;
                case 'G' :
                    switch(direc[idx]){
                        case 'u' :
                            y += 1;
                            break;
                        case 'd' :
                            y -= 1;
                            break;
                        case 'l' :
                            x -= 1;
                            break;
                        case 'r' :
                            x += 1;
                            break;
                    } break;
                case 'B' :
                    switch(direc[idx]){
                        case 'u' :
                            y -= 1;
                            break;
                        case 'd' :
                            y += 1;
                            break;
                        case 'l' :
                            x += 1;
                            break;
                        case 'r' :
                            x -= 1;
                            break;
                    }break;
            }
        }

        answer[0] = x;
        answer[1] = y;

        return answer;
    }
}