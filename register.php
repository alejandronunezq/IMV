<?php

require '/var/www/database.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = password_hash($_POST['password'], PASSWORD_BCRYPT);

    $stmt = $conn->prepare("INSERT INTO usuarios (username, password) VALUES (?, ?)");
    $stmt->bind_param("ss", $username, $password);

    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Usuario registrado"]);
    } else {
        echo json_encode(["success" => false, "message" => "Error al registrar usuario"]);
    }

    $stmt->close();
    $conn->close();
}
?>
