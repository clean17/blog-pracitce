<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

    <div class="container my-3">
        <div class="mb-3">
            <a href="/" class="btn btn-warning">수정</a>
            <button type="button" class="btn btn-danger" onclick="deleteBoard(${dto.id})">삭제</button>
        </div>

        <div class="mb-2 d-flex justify-content-end">
            글 번호 : &nbsp<span id="id">${dto.id}  &nbsp&nbsp<i>&nbsp&nbsp&nbsp&nbsp </i></span> 작성자 : ${dto.username} &nbsp<span class="me-3"><i> </i></span> 
            <i id="heart" class="fa-regular fa-heart my-xl my-cursor"></i>
        </div>
        <div>
            <h1><b>${dto.title}</b></h1>
        </div>
        <hr />
        <div>
            <div>${dto.content}</div>
        </div>
        <hr />
        <div class="card">
            <form action="/reply" method="post">
                <div class="card-body">
                    <textarea id="reply-content" name="comment" placeholder="댓글을 입력하세요 " class="form-control" rows="1"></textarea>
                </div>
                <div class="card-footer">
                    <button type="submit" id="btn-reply-save" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
        <br />
        <div class="card mt-3">
            <div class="card-header">댓글 리스트</div>
            <ul id="reply-box" class="list-group">X
                
                <li id="reply-1" class="list-group-item d-flex justify-content-between ">
                    <div class="d-flex justify-content-left">
                        <div class="font-italic">작성자 :  &nbsp;</div>
                        <div>
                                <button class="badge bg-secondary" >수정</button>
                                <button class="badge bg-secondary" >삭제</button>
                        </div>
                    </div>
                </li>
            </ul>
            
        </div>
    </div>
    <script>

        $('.summernote').summernote({
            tabsize: 2,
            height: 400
        });

        function deleteBoard(id){
            $.ajax({
                type: "delete",
                url: "/board/delete",
                dataType:"json"
            }).done((res) => {
                alert(res.msg);
                location.href="/";
            }).fail((err) => {
                alert(err.responseJSON.msg);
                location.href="/";
            });

        }
    </script>
<%@ include file="../layout/footer.jsp" %>