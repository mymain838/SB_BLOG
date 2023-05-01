let index ={
    init: function (){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
    },
    save: function (){
        let data = {

            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset = utf-8",
            dataType: "json"

        }).done(function (resp){
            if(resp.data === "OK"){
                alert("글쓰기가 완료되었습니다.");
                location.href = "/";
            }else{
                alert("실패");
            }
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
}
index.init();