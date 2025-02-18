$(function(){
    // 검색 함수
    function search() {
        const keyword = document.getElementById('searchInput').value;  // 검색어 가져오기
        const category = document.getElementById('categoryProj').value; // 카테고리 가져오기
        // URL에 검색어와 카테고리 값을 쿼리 파라미터로 추가하여 검색 페이지로 이동
        window.location.href = '/search' + `?keyword=${encodeURIComponent(keyword.trim())}&category=${category}`;
    }

// 검색 아이콘 클릭 이벤트 처리
    document.querySelector('.searchWrap .icon').addEventListener('click', function() {
        search();  // 검색 함수 실행
    });

// 검색어 입력 후 Enter 키 이벤트 처리
    document.getElementById('searchInput').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            search();  // Enter 키를 눌렀을 때 검색
        }
    });

// 카테고리 선택 시 자동 검색
    $('#categoryProj').on('change', function() {
        $(this).closest('form').submit();
    });

});