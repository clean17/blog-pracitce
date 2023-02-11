<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

    <div class="container my-3">
        <form>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Enter title" id="title" name="title" value="">
            </div>

            <div class="form-group">
                <textarea class="form-control summernote" id="content" rows="5" name="content"></textarea>
            </div>
        <button type="button" class="btn btn-primary"">글수정완료</button>
        </form>
    </div>
    <script>
        $('.summernote').summernote({
                tabsize: 2,
                height: 400
            });
    </script>
<%@ include file="../layout/footer.jsp" %>