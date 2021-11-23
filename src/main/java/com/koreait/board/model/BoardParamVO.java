package com.koreait.board.model;

public class BoardParamVO {
    private int sIdx;   // 몇 페이지까지인지 알려주는 수
    private int page;   // 해당 페이지 값
    private int recordCnt;  // 한 페이지에 보여지는 글 수

    public int getRecordCnt() {
        return recordCnt;
    }

    public void setRecordCnt(int recordCnt) {
        this.recordCnt = recordCnt;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.sIdx = (page - 1) * recordCnt;
    }

    public int getsIdx() {
        return sIdx;
    }
}
