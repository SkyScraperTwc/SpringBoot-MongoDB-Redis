package indi.twc.boot.mongodb.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {

    /** 每页显示条数 */
    private Integer pageSize;

    /** 当前页 */
    private Integer currentPage;

    /** 总页数 */
    private Integer totalPage;

    /** 查询到的总数据量 */
    private Integer totalCount;

    /** 数据集 */
    private List<T> dataList;

    public Pagination(int pageSize ,int currentPage, int totalCount) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
    }

    /**
     * 处理查询得到的结果数据
     */
    public void build(List dataList) {
        this.dataList = dataList;
        //处理总页数
        int divisor = totalCount / pageSize;
        int remainder = totalCount % pageSize;
        if (remainder == 0) {
            this.totalPage = divisor;
        } else {
            this.totalPage = divisor + 1;
        }
    }
}
