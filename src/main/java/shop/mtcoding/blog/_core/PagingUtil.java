package shop.mtcoding.blog._core;

import java.util.ArrayList;

public class PagingUtil {
    public static boolean isFirst(int currentPage) {
        return currentPage == 0 ? true : false;
    }

    public static boolean isLast(int currentPage, int totalCount) {
        int totalPageCount = getTotalPageCount(totalCount);
        return currentPage+1 == totalPageCount ? true : false;

    }

    public static int getTotalPageCount(int totalCount) {
        int remainCount = totalCount % Constant.PAGING_COUNT;
        int totalPageCount = totalCount / Constant.PAGING_COUNT;

        if (remainCount > 0) {
            totalPageCount = totalPageCount + 1;
        }
        return totalPageCount;
    }

    public static ArrayList<Integer> getPageNumber(int totalCount) {
        // 전체 게시글 수를 구하고
        // 한 페이지에 몇 개인지 구하고
        // 나눠서 딱 떨어지면 그 값
        /// 좀 남으면 +1
        int pageNumber = totalCount / Constant.PAGING_COUNT;
        if (totalCount % Constant.PAGING_COUNT != 0) {
            pageNumber = pageNumber + 1;
        }
        ArrayList<Integer> pageNumberArr = new ArrayList<>();
        for (int i = 0; i < pageNumber; i++) {
            pageNumberArr.add(i);
        }

        return pageNumberArr;
    }

    public static ArrayList<Integer> getURLNumber(int totalCount) {
        // 전체 게시글 수를 구하고
        // 한 페이지에 몇 개인지 구하고
        // 나눠서 딱 떨어지면 그 값
        /// 좀 남으면 +1
        int pageNumber = totalCount / Constant.PAGING_COUNT;
        if (totalCount % Constant.PAGING_COUNT != 0) {
            pageNumber = pageNumber + 1;
        }
        ArrayList<Integer> urlNumberArr = new ArrayList<>();
        for (int i = 0; i < pageNumber; i++) {
            urlNumberArr.add(i);
        }

        return urlNumberArr;
    }
}
