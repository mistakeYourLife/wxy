package cn.wxy.common.pageable;


import cn.wxy.common.utils.Collections3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * 分页对象, 存放单页数据 *
 */
public class Page<T> implements java.io.Serializable {
    private static final long serialVersionUID = 867755909294344406L;

    private List<T> data;
    /**
     * 总条数
     */
    private long iTotalRecords;
    /**
     * 过滤条件后查询的总数
     */
    private long iTotalDisplayRecords;
    /**
     *
     */
    private int iDisplayLength;

    // 开始显示行
    private int iDisplayStart;


    /**
     * 前台分页条件*
     */

    //总页数
    private int totalPageCount;
    //页码
    private int pageNo;
    /**
     * 最小页
     */
    private int maxPage;
    /**
     * 最大页
     */
    private int minPage;

    private int extendCount = 0;

    public int getExtendCount() {
        return extendCount;
    }

    public void setExtendCount(int extendCount) {
        this.extendCount = extendCount;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    /**
     * 每页总数
     */
    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    /**
     * 开始行
     */
    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    /**
     * 总数
     */
    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
        recalc();
    }

    public int getNumberOfElements() {
        return data.size();
    }

    public long getTotalElements() {
        return iTotalRecords;
    }

    public Iterator<T> iterator() {
        return data.iterator();
    }

    public List<T> getData() {
    	if(Collections3.isNotEmpty(data)){
    		return Collections.unmodifiableList(data);
    	}
    	return new ArrayList();
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public void setMinPage(int minPage) {
        this.minPage = minPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getMinPage() {
        return minPage;
    }

    public boolean hasContent() {
        return !data.isEmpty();
    }

    private void recalc() {
        totalPageCount = (int) Math.ceil((double) iTotalRecords / (double) iDisplayLength);
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getMinPager() {
        int _totalPageCount = getTotalPageCount();
        int _pageNo = getPageNo();
        int minPager = (_totalPageCount - _pageNo >= 4) ? (_pageNo >= 5 ? _pageNo - 4 : 1) : (_pageNo >= (5 + 4 - _totalPageCount + _pageNo) ? (_pageNo - 8 + _totalPageCount - _pageNo) : 1);
        return minPager;
    }

    public int getMaxPager() {
        int maxPager = getMinPager() + 8 > getTotalPageCount() ? getTotalPageCount() : getMinPager() + 8;
        return maxPager;
    }

	

	/*zzz
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//
//		String contentType = "UNKNOWN";
//
//		if (data.size() > 0) {
//			contentType = data.get(0).getClass().getName();
//		}
//
//		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
//	}

}
