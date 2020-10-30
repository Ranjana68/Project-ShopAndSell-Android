<?php

$con=mysqli_connect("localhost","id10155037_project1","project1",
"id10155037_project1")or die("connection not successfull");


mysqli_select_db($con,"id10155037_project1")or die("database not found");
     
    $sql = "select * from sell";

    $res = mysqli_query($con,$sql);
     
    $result = array();
     
    while($row = mysqli_fetch_array($res))
{
    array_push($result,
    array('name'=>$row[0],
    'item_name'=>$row[1],
    'phone'=>$row[2],
    'cat'=>$row[3],
    'room'=>$row[4],
    'price'=>$row[5]
    ));
    }
    echo json_encode(array("result"=>$result));
     
    mysqli_close($con);
    ?> 
	
	
	