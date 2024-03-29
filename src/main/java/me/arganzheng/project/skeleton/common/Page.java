package me.arganzheng.project.skeleton.common;

import java.util.Collections;
import java.util.List;

import me.arganzheng.project.skeleton.criteria.PagingCriteria;

import org.apache.commons.collections.CollectionUtils;

public class Page<T> {

    private int               recordCount = 0;
    private int               pageIndex   = 1;
    private int               pageCount   = 0;
    private int               pageSize    = 10;
    private List<T>           records     = Collections.emptyList();

    @SuppressWarnings("rawtypes")
    private static final Page emptyPage   = new Page();

    public boolean isNotEmpty() {
        return recordCount > 0;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public boolean isMultiplePages() {
        return pageCount > 1;
    }

    public boolean isNotLastPage() {
        return pageIndex != pageCount;
    }

    public boolean isNotFirstPage() {
        return pageIndex != 1;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public static <T> Page<T> createInstance(List<T> items, int recordCount, int pageIndex, int pageSize) {
        if (pageSize < 1 || recordCount < 0 || pageIndex < 1) {
            throw new IllegalArgumentException("Invalid page parameter: recordCount: " + recordCount + ", pageIndex:"
                                               + pageIndex + ", pageSize: " + pageSize);
        }
        int pageCount = calculatePageCount(recordCount, pageSize);
        Page<T> page = new Page<T>();
        if (CollectionUtils.isNotEmpty(items)) {
            page.records = items;
        }
        page.recordCount = recordCount;
        page.pageIndex = pageIndex;
        page.pageCount = pageCount;
        page.pageSize = pageSize;
        return page;
    }

    public static <T1, T2> Page<T1> createInstanceFrom(Page<T2> templatePage, List<T1> items) {
        Page<T1> page = new Page<T1>();
        if (CollectionUtils.isNotEmpty(items)) {
            page.records = items;
        }
        page.recordCount = templatePage.recordCount;
        page.pageIndex = templatePage.pageIndex;
        page.pageCount = templatePage.pageCount;
        page.pageSize = templatePage.pageSize;
        return page;
    }

    @SuppressWarnings("unchecked")
    public static <T> Page<T> emptyPage() {
        return (Page<T>) emptyPage;
    }

    public static int calculatePageCount(int recordCount, int pageSize) {
        int result = recordCount / pageSize;
        if (recordCount % pageSize != 0) {
            result++;
        }
        return result;
    }

    public static <T> Page<T> createInstance(List<T> recordsInAPage, int recordCount, PagingCriteria pagingCriteria) {
        return Page.createInstance(recordsInAPage, recordCount, pagingCriteria.getPageIndex(),
                                   pagingCriteria.getPageSize());
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
