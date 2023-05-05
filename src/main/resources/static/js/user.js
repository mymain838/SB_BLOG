let index = {
    init: function () {
        $("#btn-save").on("click", () => { //this를 바인딩하기 위해서!!
            this.save();
        });
        $("#btn-update").on("click", () => { //this를 바인딩하기 위해서!!
            this.update();
        });
       /* $("#btn-login").on("click", () => { //this를 바인딩하기 위해서!!
            this.login();
        });*/

    },

    save: function () {
        // alert('user의 save함수 호출됌');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
       // console.log(data);
        //ajax호출 default가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), //http bodat 데이터
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){

            alert("회원가입이 완료되었습니다." + resp.data);
           console.log(resp);
            location.href= "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        // alert('user의 save함수 호출됌');
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            oauth: $("#oauth").val()
        };
        console.log(data);
        //ajax호출 default가 비동기 호출
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), //http bodat 데이터
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){
            if(resp.data==="OK"){
                alert("회원수정이 완료되었습니다");
                location.href= "/";
            }else{
                alert(JSON.stringify(resp));
            }



        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
   /* login: function () {
        // alert('user의 save함수 호출됌');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        // console.log(data);
        //ajax호출 default가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data), //http bodat 데이터
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){

            alert("로그인이 완료되었습니다." + resp.data);
            console.log(resp);
            location.href= "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }*/
}

index.init();