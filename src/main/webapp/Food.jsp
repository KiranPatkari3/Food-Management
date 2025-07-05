<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>





<!--  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>

@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');


*{
	margin: 0px;
	padding: 0px;
	font-family: "Poppins", sans-serif;
}


.dot_orange{
    background-color: #ff9933;
    width: 350px;
    height: 350px;
    border-radius: 50%;
    filter: blur(350px);
    position: absolute;
    left: 250px;
    top: 250px;
    z-index: -1;
}

.dot_violet{
    background-color: #cc33ff;
    width: 350px;
    height: 350px;
    border-radius: 50%;
    filter: blur(350px);
    position: absolute;
    right: 250px;
    top: 250px;
    z-index: -1;
}

.container{
	width: 80%;
	margin: auto;
	display: flex;
		justify-content: space-between;
		padding: 20px;
}
	.nav{
		box-shadow: 0px 0px 10px 2px #f2f2f2;
		padding: 0px 30px;
		background: #fff;
		margin-bottom: 20px;
		
	}
	
	.nav button{
		background: #000;
		color: #fff;
		padding: 10px 20px;
		border-radius: 50px;
		border: none;
	}
	
	.nav h3{
		font-weight: 500;
		font-size: 22px;
	}
	.food{
		width: 360px;
		display: inline-block;
		margin: 20px;
		background: #fff;
		
		
	}
	
	.food img{
		width: 100%;
		height: 200px;
		
	}
	
	.foods{
		width: 80%;
		margin: auto;
	}
	
	.food button{
		border: none;
		border-radius: 20px;
		color: #fff;
		background: #000;
		padding: 2px 10px;
		margin-top: 15px;
		border: 2px solid #000;
	}
	
	.food button:hover{
		color: #000;
		background: #fff;
	}
	
	.fa{
	border: 1px solid red;
		border-radius: 20px;
		color: red;
		padding: 6px 8px;
		margin-top: 15px;
		position: relative;
		left: 240px;
		top: 2px;
	}
	
	.fa:hover{
	background: red;
	color: #fff;
	}
	
	.footer{
		text-align: center;
		padding: 50px;
		margin-top: 150px;
		background: #fff;
	}
	
	
	
</style>
</head>
<body>


<div class="nav">
<div class="container">
<h3>🍔 FoodApp</h3>
<a href="<%=request.getContextPath()%>/add-food"><button>Add Food</button></a>
</div>
</div>

<div class="dot_orange"></div>
<div class="dot_violet"></div>

<div class="foods">


<c:forEach var="food" items="${listFood}">

						
						
						
	<div class="food">
		<img src="<c:out value="${food.image}" />" />
		
		<div style="padding: 20px;">
		<h3><c:out value="${food.name}" /></h3>
		<div style="display: flex; justify-content: space-between; margin-top: 10px;">
			<p>Rs. <c:out value="${food.price}" /> /-</p>
			<p><c:out value="${food.rating}" /> ⭐</p>
		</div>
		<a href="edit?id=<c:out value='${food.id}' />"><button>Edit</button></a>
		<a href="delete?id=<c:out value='${food.id}' />"><i class="fa fa-trash"></i></a>
		</div> 
		
	</div>
</c:forEach>
					

</div>

<div class="footer">
<p>Created by Kiran Patkari</p>
</div>
</body>
</html> 
-->