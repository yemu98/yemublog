<?php
// echo '这是ajax';
header('Content-Type:application/json; charset=utf-8');
$arr = array('a'=>1,'b'=>2,'c'=>'哪');
echo json_encode($arr,JSON_UNESCAPED_UNICODE);
?>