<?php

$con=mysqli_connect("localhost","id10155037_project1","project1",
"id10155037_project1")or die("connection not successfull");


mysqli_select_db($con,"id10155037_project1")or die("database not found");

if(isset($_POST['name']) && isset($_POST['item_name'])&&isset($_POST['phone'])&&isset($_POST['cat'])&&isset($_POST['room'])&&isset($_POST['price']))
{

$name=$_POST['name'];
$item_name=$_POST['item_name'];
$phone=$_POST['phone'];
$cat=$_POST['cat'];
$room=$_POST['room'];
$price=$_POST['price'];

$qry="insert into sell (name,item_name,phone,cat,room,price) values('$name','$item_name','$phone','$cat','$room','$price')";

mysqli_query($con,$qry)or die("Query Problem"); 
}
else
{
echo "waiting for data...";
}
?>