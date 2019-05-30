package study.jsp.helper;

public class PageHelper {
	
	/**GET파라미터로 처리할 값*/
	private int page = 1;				//현재 페이지 번호
	
	/**DB에서 조회할 값*/
	private int totalCount = 0;			//전체 글 개수 조회
	
	/**개발자가 정의해야 하는 값*/
	private int listCount = 10;			//한 페이지에 보여질 글의 목록 수
	private int groupCount = 5;			//한 그룹에 표시할 페이지번호 개수
	
	/**연산처리가 필요한 값*/
	private int totalPage = 0;			//전체페이지 수
	private int startPage = 0;			//현재 그룹의 시작 페이지
	private int endPage = 0;			//현재 그룹의 마지막 페이지
	private int prevPage = 0;			//이전 그룹의 마지막 페이지
	private int nextPage = 0;			//다음 그룹의 첫 페이지
	private int limitStart = 0;
	
	//------------------------------싱글톤 객체 생성 시작----------------------------------
	private static PageHelper current = null;
	
	public static PageHelper getInstance(int page, int totalCount, int listCount, int groupCount) {
		if(current == null) {
			current = new PageHelper();
		}
		//객체 생성 후, 계산에 필요한 값들을 연산처리를 위한 메서드에 전달한다.
		current.pageProcess(page, totalCount, listCount, groupCount);
		
		return current;
	}
	
	public static void freeInstance() {
		current = null;
	}
	
	private PageHelper() {
		super();
	}
	
	/**
	 * 페이지 구현에 필요한 계산식을 처리하는 메서드
	 * 메서드를 호출하는 쪽으로부터
	 * 현재페이지, 전체 게시글 개수, 한 페이지에 보여질 글의 목록 수, 한 그룹에 표시할 페이지 개수를 
	 * 전달받는다.
	 * */
	private void pageProcess(int page, int totalCount, int listCount, int groupCount) {
		this.page = page;
		this.totalCount = totalCount;
		this.listCount = listCount;
		this.groupCount = groupCount;
		
		//전체페이지 수 --> 공식
		totalPage = ((totalCount-1)/listCount)+1;
		
		//현재 페이지에 대한 오차 조절
		if(page < 0) {	//만약 전달받은 페이지가 0보다 작다면..?..미친놈인가
			page = 1;	//그냥 1페이지로 하기로 한다
		}
		if(page>totalPage) {	//만약 전달받은 페이지가 전체 페이지 수보다 큰 값을 가지고있다면?
			page = totalPage;	//그냥 전체페이지개수(마지막페이지 번호)로 하기로 한다.
		}
		
		//현재페이징 그룹의 시작 페이지 번호
		startPage = ((page-1)/groupCount)*groupCount+1;
		
		//현재페이징 그룹의 끝 페이지 번호
		endPage = (((startPage-1) + groupCount) / groupCount) * groupCount;
		
		//끝페이지 번호가 전체 페이지수를 초과하면 오차범위 조절
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		//이전 그룹의 마지막 페이지
		if(startPage > groupCount) {	//현재 그룹의 첫번째 페이지가 한 그룹의 페이지 개수보다 크다면,
			prevPage = startPage-1;		//이전페이지 그룹의 마지막페이지는 현재그룹의 첫번쨰페이지-1
		}else {
			nextPage = 0;				//현재 그룹의 첫번째페이지가 한 그룹의 페이지개수보다 작다면, 
		}								//다음 페이지 그룹의 첫번째 페이지는 0이다
		
		//다음 그룹의 첫 페이지
		if(endPage < totalPage) {		//만약 현재그룹의 마지막페이지가  전체페이지 개수보다 작다면
			nextPage = endPage+1;		//다음 그룹의 첫 페이지는 현재그룹의 마지막페이지 +1;
		}else {							//만약 현재그룹의 마지막페이지가 전체페이지 개수보다 크다면,
			nextPage = 0;				
		}
		
		//현재 페이징 그룹의 시작 페이지 번호
		startPage = ((page-1)/groupCount)*groupCount+1;
		
		//현재페이징 그룹의 끝 페이지 번호
		endPage = (((startPage-1)+groupCount)/groupCount)*groupCount;
		
		//끝페이지 번호가 전체 페이지수를 초과하면 오차범위 조절
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		//이전 그룹의 마지막 페이지
		if(startPage > groupCount) {
			prevPage = startPage-1;
		}else {
			prevPage = 0;
		}
		
		//다음그룹의 첫 페이지
		if(endPage<totalPage) {
			nextPage = endPage+1;
		}else {
			nextPage = 0;
		}
		
		//검색범위의 시작 위치
		limitStart = (page-1)*listCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}

	@Override
	public String toString() {
		return "PageHelper [page=" + page + ", totalCount=" + totalCount + ", listCount=" + listCount + ", groupCount="
				+ groupCount + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", limitStart=" + limitStart + "]";
	}
	
	
}

