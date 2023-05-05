<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp" %>

<div class="container">

    <form class="form-inline justify-content-end" action="/" method="get">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" id="keyword" name="keyword">
        <button class="btn btn-success" type="submit">Search</button>
    </form>
    ${principal.user.password}<p/>
    ${principal.user.email}
    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination d-flex justify-content-center">

        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}&keyword=${keyword}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <c:choose>
                <c:when test="${i eq boards.number+1}">
                    <li class="page-item disabled"><a class="page-link" href="?page=${i-1}&keyword=${keyword}">${i}</a></li>
                </c:when>

                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?page=${i-1}&keyword=${keyword}">${i}</a></li>
                </c:otherwise>
            </c:choose>

        </c:forEach>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}&keyword=${keyword}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

</div>

<%@ include file="layout/footer.jsp" %>




