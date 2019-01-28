<?php
header('Access-Control-Allow-Origin:*');//注意！跨域要加这个头
$student = $_POST['student'];
echo json_encode($student,JSON_UNESCAPED_UNICODE);
echo $student[0]['name'];
// echo $student['age'];
// echo $student['sex'];
echo($_SERVER['HTTP_HOST']);
?>