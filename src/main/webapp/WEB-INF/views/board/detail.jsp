<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <div>
        글번호 : <span id="id" >${board.id}</span>
        작성자 : <span id="username" >${board.user.username}</span>
        조회수 : <span id="count" >${board.count}</span>
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

</div>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>

