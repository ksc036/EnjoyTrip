



window.addEventListener('load', () =>  {

    /* button이 클릭되었을때 이벤트 */

    var httpRequest = new XMLHttpRequest();

    document.getElementById("loginRequest").addEventListener('click', () => {
        var inputEmail = document.getElementById("check-Email").value;
        var inputPassword = document.getElementById("check-Password").value;
        httpRequest.onreadystatechange = loginprocess;
        
        httpRequest.open('post','./user' , true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.send('action=login&email=' + encodeURIComponent(inputEmail) + '&password=' + encodeURIComponent(inputPassword));

    });


    function loginprocess() {

        if (httpRequest.readyState == 4 && httpRequest.status == 200) {
            var result = httpRequest.responseText;
            if (result == "success") {
                alert('로그인 성공');
                window.location.replace("home.jsp");
            } else {
                alert('로그인 실패');
            }

        }

    }
}


);

    


