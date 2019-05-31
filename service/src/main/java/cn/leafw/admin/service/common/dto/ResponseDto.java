package cn.leafw.admin.service.common.dto;

import lombok.Data;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/4 14:46
 */
@Data
public class ResponseDto<T> {

    /**返回代码**/
    private boolean isSucc;
    /**返回结果**/
    private String resultMsg;
    /**返回数据**/
    private T data;

    public ResponseDto(boolean isSucc, String resultMsg, T data) {
        this.isSucc = isSucc;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    private ResponseDto(T data){
        this.isSucc = true;
        this.resultMsg ="成功";
        this.data = data;
    }
    private ResponseDto(String resultMsg){
        this.isSucc = false;
        this.resultMsg = resultMsg;
    }

    public ResponseDto() {
    }

    public static <T> ResponseDto returnSucc(T data){
        return new ResponseDto(data);
    }

    public static ResponseDto returnFalse(String errMsg){
        return new ResponseDto(errMsg);
    }
}
