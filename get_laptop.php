<?php
header('Content-Type: application/json');
require 'database.php';

if (isset($_GET['n_s'])) {
    $n_s = $_GET['n_s'];

    $stmt = $pdo->prepare('
        SELECT laptops.marca, laptops.modelo, laptops.mac, laptops.ram, laptops.almacenamiento, laptops.anydesk, 
               usuarios.nombre AS usuarioAsignado, usuarios.correo, usuarios.puesto, usuarios.departamento
        FROM laptops
        JOIN usuarios ON laptops.uId = usuarios.id
        WHERE laptops.n_s = :n_s ');

    $stmt->execute(['n_s' => $n_s]);

    $laptop = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($laptop) {
        echo json_encode($laptop);
    } else {
        echo json_encode(['error' => 'Laptop no encontrada']);
    }
} else {
    echo json_encode(['error' => 'No se proporcionó el número de serie']);
}
?>
