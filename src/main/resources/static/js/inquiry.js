$(function() {
    // 이메일 도메인 선택 처리
    $("select[name='emailDomain']").change(function() {
        var domain = $(this).val();  // select에서 선택한 값 가져오기

        // 이메일 도메인에 따라 email02 필드 값을 설정
        if (domain === "직접입력") {
            $("input[name='email02']").prop("disabled", false);  // email02 활성화
            $("input[name='email02']").val("");  // 기존 입력값 지우기
            $("#hiddenEmailDomain").val("");  // hidden 필드도 초기화
        } else {
            $("input[name='email02']").prop("disabled", true);  // email02 비활성화
            $("input[name='email02']").val(domain);  // email02에 선택된 도메인값 넣기
            $("#hiddenEmailDomain").val(domain);  // hidden 필드에 선택된 도메인값 넣기
        }
    });



    // 폼 제출 전 유효성 검사
    $(".inquiryForm").submit(function(e) {
        // 개인정보 수집 동의 체크 확인
        if (!$(".agreeChk input[type='checkbox']").is(":checked")) {
            alert("개인정보 수집에 동의해주세요.");
            e.preventDefault();
            return false;
        }
    });
});

