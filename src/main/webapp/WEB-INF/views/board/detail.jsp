<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <div>
        글번호 : <span id="id">${board.id}</span>
        작성자 : <span id="username">${board.user.username}</span>
        조회수 : <span id="count">${board.count}</span>
    </div>

    <br/>

    <div>
        <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
        <c:if test="${board.user.id eq principal.user.id}">
            <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
            <button id="btn-delete" class="btn btn-danger">삭제</button>
        </c:if>

    </div>
    <hr/>
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div>
        ${board.content}
    </div>

    <div class="card">

        <input type="hidden" id="boardId" value="${board.id}"/>
        <input type="hidden" id="userId" value="${principal.user.id}"/>
        <div>
            <div class="card-body"><textarea id="reply-content" class="form-control summernote" rows="1"></textarea></div>
            <div class="card-footer d-flex align-items-center">
                <div style="flex-grow: 2;">
                    <div class="d-flex justify-content-around">

                        <div class="custom-control custom-radio">
                            <input type="radio" name="r1" id="r1" class="custom-control-input">
                            <label class="custom-control-label text-danger" for="r1">빨강</label>
                        </div>
                        <div class="custom-control custom-radio ">
                            <input type="radio" name="r2" id="r2" class="custom-control-input">
                            <label class="custom-control-label text-info" for="r2">파랑</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" name="r3" id="r3" class="custom-control-input">
                            <label class="custom-control-label text-warning" for="r3">노랑</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" name="r4" id="r4" class="custom-control-input">
                            <label class="custom-control-label text-success" for="r4">초록</label>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-end" style="flex-grow: 1; ">
                    <button id="btn-reply-save" class="btn btn-primary">등록</button>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="card m-3">
    <div class="card-header">댓글 리스트</div>
    <div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply--${reply.id}" class="list-group-item list-group-item-${reply.color} d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                        <button class="badge badge-info "style="height: 20px">삭제</button>
                    </div>


                </li>
            </c:forEach>

            <li class="list-group-item list-group-item-light">Light item</li>
        </ul>
    </div>
</div>

</div>

<script>
    $('.summernote').summernote({
        placeholder: '댓글을 입력해주세요 ',

    });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>

