package com.machi.springbootweb.util;

/**
 * Created by dell on 2018/3/1.
 */


public class ReturnUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }


}
