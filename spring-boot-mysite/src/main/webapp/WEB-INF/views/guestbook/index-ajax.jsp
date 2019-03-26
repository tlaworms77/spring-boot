<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style type="text/css">
	#dialog-delete-form p {
		padding: 10px;
		font-weight: bold;
		font-size: 1.0em;
	}
	
	#dialog-delete-form input[type="password"] {
		padding: 5px;
		outline: none;
		width: 180px;
		border: 1px solid #888888;
	}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	// jquery plug-in
	(function(e){
		$.fn.hello = function(){
			console.log($(this).attr("id") + "----> hello");
		}
	})(jQuery);
	
	var page = 0;
	var isEnd = false;
	
	var messageBox = function(title, message, id){
		$("#dialog-message").attr("title", title);
		$("#dialog-message p").text(message);
		
		$("#dialog-message").dialog({
			modal: true,
			buttons: {
				"확인": function(){
					console.log("확인 버튼 클릭");
					$(this).dialog("close");
					$(id).focus();
				},
				"취소": function(){
					console.log("취소 버튼 클릭");
					$(this).dialog("close");
					$(id).focus();
				}
			}
		});
	};
	
	var render = function(vo, mode){
		// 현업에 가면 이렇게 안한다. -> js template Libarary 사용
		// ex) ejs, underscore, mustache
		
		var htmls = "<li data-no='" + vo.no + "'>" +
					"<strong>" + vo.name + "</strong>" +
					"<p>" + vo.message.replace(/\n/gi, "<br>") + "</p>" +
					"<strong></strong>" +
					"<a href=' ' data-no= '" + vo.no + "'>삭제</a>" +
					"</li>";
		
		if(mode){
			$("#list-guestbook").prepend(htmls);
		} else{
			$("#list-guestbook").append(htmls);
		}			
	}
	
	var fetchList = function(){
		if(isEnd){
			return;
		}
		
		++page;
				
		$.ajax({
			async:true,
			url:"${pageContext.servletContext.contextPath }/guestbook/api/ajax-list?page=" + page,
			type:"get",
			dateType:"json",
			data:"",
			success:function(response){
				console.log(response);
				
				if(response.result == "fail"){
					console.warn(response.data);
							
					return;
				}
						
				console.log(response.data);
						
				// 페이지 끝을 검출
				if(response.data.length < 5){
					isEnd = true;
							
					$("#btn-next").prop("disabled", true);
				}
						
				// rendering
				$.each(response.data, function(index, vo){
					console.log(vo);
							
					render(vo, false);
				});
			},
			error:function(xhr, status, e){
				console.error(status + " : " + e);
			}
		});	
	};
	
	$(function(){
// 		var dialogDelete = $("#dialog-delete-form").dialog({
// 			autoOpen: false,
// 			modal: true,
// 			buttons: {
// 				"삭제": function(){
// 					console.log("ajax 삭제 작업");
					
// 					$.ajax({
// 						async: true,
// 						url: "${pageContext.servletContext.contextPath }/guestbook/api/ajax-delete?page=" + page + "&password=" + $("#password-delete").val() + "&no=" + $("#hidden-no").val(),
// 						type: "get",
// 						dataType: "json",
// 						data: "",
// 						success: function(response){
// 							console.log("delete ajax");
// 							console.log(response);
// 							console.log(response.data);
							
// 							if(response.data == false){
// 								console.log(response.data);
								
// 								$(".validateTips-error").show();
// 								$("#password-delete").val("");
// 							}
// 							else{
// 								console.log("remove");
								
// 								dialogDelete.dialog("close");
								
// 								$('#list-guestbook li[data-no=' + $("#hidden-no").val() + ']').remove();
// 							}
// 						},
// 						error: function(xhr, status, e){
// 							console.log("delete fail ajax");
// 						}
// 					});
// 				},
// 				"취소": function(){
// 					dialogDelete.dialog("close");
// 				}
// 			},
// 			close: function(){
// 				$("#password-delete").val("");
// 				$(".validateTips-error").hide();
				
// 				console.log("close시 뒤처리");
// 			}
// 		});
		
// 		// Live 이벤트
// 		$(document).on("click", "#list-guestbook li a", function(event){
// 			event.preventDefault();
			
// 			console.log("clicked " + $(this).data("no"));
// 			$("#hidden-no").val($(this).data("no"));
			
// 			dialogDelete.dialog("open");
// 		});
		
// 		// message 등록 폼 submit 이벤트
// 		$("#add-form").submit(function(event){
// 			// submit의 기본동작 (post)
// 			// 막아야한다.
// 			event.preventDefault();
			
// 			// validate form data
// 			var name = $("#input-name").val();
// 			var password = $("#input-password").val();
// 			var content = $("#tx-content").val();
			
// 			if(name == ""){
// 				// alert("이름은 필수입력 항목입니다.");
// 				messageBox("글 남기기", "이름은 필수입력 항목입니다.", "#input-name");
				
// 				$("#input-name").focus();
				
// 				return;     
// 			}
			
// 			if(password == ""){
// 				messageBox("글 남기기", "비밀번호는 필수입력 항목입니다.", "#input-password");
				
// 				$("#input-password").focus();
				
// 				return;
// 			}
			
// 			if(content == ""){
// 				messageBox("글 남기기", "내용은 필수입력 항목입니다.", "#tx-context");
				
// 				$("#tx-context").focus();
				
// 				return;
// 			}
			
// 			$.ajax({
// 				async: true,
// 				url: "${pageContext.servletContext.contextPath }/guestbook/api/ajax-insert/" + page + "/" + $("#input-name").val() + "/" + $("#input-password").val() + "/" + $("#tx-content").val(),
// 				type: "get",
// 				dataType: "json",
// 				data: "",
// 				success: function(response){
// 					console.log(response);
					
// 					console.log(response.data);
					
// 					render(response.data, true);
					
// 					$("#input-name").val("");
// 					$("#input-password").val("");
// 					$("#tx-content").val("");
// 				},
// 				error: function(xhr, status, e){
// 					console.log(status + " : " + e);
// 				}
// 			});
// 		});
		
// 		// scroll 이벤트
// 		$(window).scroll(function(){
// 			var $window = $(this);
// 			var scrollTop = $window.scrollTop();
// 			var windowHeight = $window.height();
// 			var documentHeight = $(document).height();
			
// 			console.log(scrollTop);
// 			console.log(windowHeight);
// 			console.log(documentHeight);
			
// 			// 끝까지 scroll 했을 경우
// 			if(scrollTop + windowHeight + 10 > documentHeight){
// 				console.log("fetch ajax start");
				
// 				fetchList();
// 			}
// 		});	
		
// 		$("#btn-next").click(function(){
// 			$(this).hello();
// 			fetchList();
// 		});
	
		// 최초 리스트 가져오기
		fetchList();
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름"> 
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>

				<button id="btn-next">다음</button>
				
				<ul id="list-guestbook">
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<li>
							<table>
								<tr>
									<td>[${count - status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<td><a href="${pageContext.servletContext.contextPath }/guestbook/delete/${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan="4">${fn:replace(vo.message, newLine, "<br>") }</td>
								</tr>
							</table>
							<br>
						</li>						
					</c:forEach>
				</ul>
			</div>
			
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips-normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips-error" style="display: none">비밀번호가 틀립니다.</p>
				
				<form id="delete-form" action="" method="post">
					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all"> 
					<input type="hidden" id="hidden-no" value=""> 
					<input type="submit" tabindex="-1" style="position: absolute; top: -1000px">
				</form>
			</div>
			
			<div id="dialog-message" title="" style="display: none">
				<p style="padding:30px 0"></p>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>