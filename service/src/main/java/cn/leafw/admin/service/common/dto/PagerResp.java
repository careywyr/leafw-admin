package cn.leafw.admin.service.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/9 15:04
 */
@Data
public class PagerResp<T> {

    private int totalPage;

    private long totalRow;

    private int pageNumber;

    private int pageSize;

    private List<T> data;

    private boolean isSucc;

    private String resultMsg;

    public PagerResp(int totalPage, long totalRow, int pageNumber, int pageSize, List<T> data, boolean isSucc, String resultMsg) {
        this.totalPage = totalPage;
        this.totalRow = totalRow;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.data = data;
        this.isSucc = isSucc;
        this.resultMsg = resultMsg;
    }

    public PagerResp(int totalPage, long totalRow,  List<T> data) {
        this.totalPage = totalPage;
        this.totalRow = totalRow;
        this.data = data;
        this.resultMsg = "成功";
        this.isSucc = true;
    }

    public PagerResp(String resultMsg) {
        this.isSucc = false;
        this.resultMsg = resultMsg;
    }

    public PagerResp() {
    }

    public static <T> PagerResp<T> returnSucc(int totalPage, long totalRow, List<T> data){
        return new PagerResp<>(totalPage,totalRow, data);
    }

    public static <T> PagerResp<T> returnFalse(String errMsg){
        return new PagerResp<>(errMsg);
    }
}
