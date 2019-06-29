package yemu.Common;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

//@JSONType(serialzeFeatures = {SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty})
public class Response<T> implements Serializable{
    @JSONField(ordinal = 1)
    private int status;
    @JSONField(ordinal = 2)
    private String msg;
    @JSONField(ordinal = 3)
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    private Response(int status){
        this.status=status;
    }
    private Response(int status,T data){
        this.status=status;
        this.data=data;
    }
    private Response(int status,String msg){
        this.status=status;
        this.msg=msg;
    }
    private Response(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public static <T> Response<T> createRespBySuccess(){
        return new Response<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> Response<T> createRespBySuccess(T data){
        return new Response<>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> Response<T> createRespBySuccess(String msg,T data){
        return new Response<>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
    public static <T> Response<T> createRespBySuccessMsg(String msg){
        return new Response<>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> Response<T> createRespByError(){
        return new Response<>(ResponseCode.ERROR.getCode());
    }
    public static <T> Response<T> createRespByErrorMsg(String msg){
        return new Response<>(ResponseCode.ERROR.getCode(),msg);
    }
    public static <T> Response<T> createRespByErrorCodeMsg(int errorCode,String msg){
        return new Response<>(errorCode,msg);
    }
}
