$(function() {
    // select 값 변경 시 처리하는 함수
    $("select[name='emailDomain']").change(function() {
        var domain = $(this).val();  // select에서 선택한 값 가져오기

        // 이메일 도메인에 따라 email02 필드 값을 설정
        if (domain === "직접입력") {
            $("input[name='email02']").prop("disabled", false);  // email02 활성화
            $("input[name='email02']").val("");  // 기존 입력값 지우기
        } else {
            $("input[name='email02']").prop("disabled", true);  // email02 비활성화
            $("input[name='email02']").val(domain);  // email02에 선택된 도메인값 넣기
        }
    });
});
