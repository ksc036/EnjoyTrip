
window.addEventListener('load', () =>  {

    /* button이 클릭되었을때 이벤트 */

    var joinRequest = new XMLHttpRequest();

    document.getElementById("idcheckRequest").addEventListener('click', () => {
        var joinEmail = document.getElementById("join-email").value;
        
        joinRequest.onreadystatechange = joinprocess;
        
        joinRequest.open('post','./user' , true);
        joinRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        joinRequest.send('action=idcheck&email=' + encodeURIComponent(joinEmail));

    });

    
    function joinprocess() {
        if (joinRequest.readyState == 4 && joinRequest.status == 200) {
            var result = joinRequest.responseText;
            if (result == "success") {
                alert('이메일 확인 성공');
                document.getElementById("joinEmail").innerHTML = '<span style = "color : LightSkyBlue;" > <strong>사용가능한 이메일입니다</strong></span>';
            	document.getElementById("joinbtn").type = "submit";
            	document.getElementById("joinbtn").removeAttribute( 'onclick' );
            } else {
                alert('이메일 확인 필요');
                document.getElementById("joinEmail").innerHTML = '<span style = "color : Crimson;" > <strong>이메일 중복 혹은 형식 문제!</strong></span>';
            	document.getElementById("joinbtn").type = "button";
            	document.getElementById("joinbtn").setAttribute( 'onclick', "alert('이메일 확인 필요')" )
            }

        }

    }
}

	);


window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');

    Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {

            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
                alert("유효성 검사  필요 ");

            } else{
            	alert('회원가입 완료')
            }
            form.classList.add('was-validated');

        }, false);


    });

}, false);



// 구군 코드 받는 비동기
window.addEventListener('load', () =>  {

    /* 변화 생겼을 때 발생*/

    var gugunRequest = new XMLHttpRequest();

    document.getElementById("sido_select").addEventListener('change', () => {
        var sido_code = document.getElementById("sido_select").value;
        gugunRequest.onreadystatechange = gugunprocess;
        gugunRequest.open('get','./user?action=gugun_map&sido_code='  + sido_code  , true);
        gugunRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        gugunRequest.send();

    });

    
    function gugunprocess() {
        if (gugunRequest.readyState == 4 && gugunRequest.status == 200) {
            var result = gugunRequest.responseText;
            var gugun = JSON.parse(result);

            
            var $gugun =  $("select[name^=gugun_code]");
            $("option",$gugun).remove();
            $gugun.append("<option value=''>구/군 선택</option>");
            
     	   $.each(gugun, function(key, value) {
    	
     			 $gugun.append("<option value='"+key+"'>"+value +"</option>");
    	   });
        }
    }
}
);

    	