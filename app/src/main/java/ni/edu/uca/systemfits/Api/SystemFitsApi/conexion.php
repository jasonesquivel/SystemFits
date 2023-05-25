<?php

$mysql = new mysqli("localhost","root","", "alimentos");

if($mysql->connect_error){

    echo "Error: "; 
    
}

?>