<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FoodApp | Add Food</title>
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
		padding: 0px 20px;
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
	
	.add_food{
		background: #fff;
		padding: 50px;
		width: 40%;
		margin: auto;
		margin-top: 50px;
	}
	
	.add_food h2{
	margin-bottom: 20px;
	}
	
	.add_food label{
		color: grey;
		margin-bottom: 20px;
	}
	
	.add_food input{
		padding: 10px;
		width: 100% !important;
		box-sizing: border-box;
		margin-bottom: 30px;
		border: 2px solid #f2f2f2;
		border-bottom: 2px solid #000;
		
	}
	
	.add_food button{
		background: #000;
		color: #fff;
		padding: 10px 20px;
		border-radius: 50px;
		border: none;
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
<a href="<%=request.getContextPath()%>/food"><button>Back</button></a>
</div>
</div>

<div class="dot_orange"></div>
<div class="dot_violet"></div>

<div class="add_food">
<h2>Add Food</h2>

<form action="insert" method="post">
<label>Food Name</label><br>
<input name="name" placeholder="Enter Food Name"/>

<label>Food Image URL</label><br>
<input name="image" placeholder="Enter Food Image URL"/>

<div style="display: flex; gap: 30px;">
<div style="width: 50%">
<label>Food Price</label><br>
<input name="price" placeholder="Enter Food Price"/>
</div>

<div style="width: 50%">
<label>Food Rating</label><br>
<input name="rating" placeholder="Enter Food Rating"/>

</div>
</div>



<button type="submit">Add Food</button>
</form>

</div>

<div class="footer">
<p>Created By Kiran Patkari</p>
</div>
</body>
</html>