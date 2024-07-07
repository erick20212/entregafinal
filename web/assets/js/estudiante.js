function agregarEstudiante() {
    var datos = $('#agregarEstudianteForm').serialize();
    $.ajax({
        url: 'EstudianteController?action=create',
        type: 'POST',
        data: datos,
        success: function () {
            $('#agregarEstudianteModal').modal('hide'); // Ocultar el modal
            alert('Estudiante agregado correctamente');
            location.reload(); // Refrescar la página
        },
        error: function () {
            alert('Error al agregar estudiante');
        }
    });
}
function cargarEstudiantes() {
    $.ajax({
        url: 'EstudianteController?action=list',
        type: 'GET',
        dataType: 'json',
        success: function(estudiantes) {
            // Ordenar estudiantes por idestudiante
            estudiantes.sort(function(a, b) {
                return a.idestudiante - b.idestudiante;
            });

            var html = '';
            $.each(estudiantes, function(index, est) {
                html += '<tr>' +
                            '<td>' + est.idestudiante + '</td>' +
                            '<td>' + est.nombre + '</td>' +
                            '<td>' + est.apellido + '</td>' +
                            '<td>' +
                                '<button onclick="editarEstudiante(' + est.idestudiante + ')" class="btn btn-primary">Editar</button>' +
                                '<button onclick="eliminarEstudiante(' + est.idestudiante + ')" class="btn btn-danger">Eliminar</button>' +
                            '</td>' +
                        '</tr>';
            });
            $('#estudiantes-list').html(html);
        },
        error: function() {
            alert('No se pudo cargar la lista de estudiantes');
        }
    });
}


function editarEstudiante(id) {
    $.ajax({
        url: 'EstudianteController?action=edit',
        type: 'GET',
        data: {id: id},
        dataType: 'json',
        success: function (estudiante) {
            $('#edit_idestudiante').val(estudiante.idestudiante);
            $('#edit_nombre').val(estudiante.nombre);
            $('#edit_apellido').val(estudiante.apellido);
            $('#editarEstudianteModal').modal('show');
        },
        error: function () {
            alert('Error al cargar datos del estudiante');
        }
    });
}

function actualizarEstudiante() {
    var datos = $('#editarEstudianteForm').serialize();
    $.ajax({
        url: 'EstudianteController?action=update',
        type: 'POST',
        data: datos,
        success: function () {
            $('#editarEstudianteModal').modal('hide');
            cargarEstudiantes();
            alert('Estudiante actualizado correctamente');
        },
        error: function () {
            alert('Error actualizando estudiante');
        }
    });
}

function eliminarEstudiante(id) {
    if (confirm("¿Estás seguro de querer eliminar este estudiante?")) {
        $.ajax({
            url: 'EstudianteController?action=delete',
            type: 'POST',
            data: {id: id},
            success: function () {
                cargarEstudiantes();
                alert('Estudiante eliminado correctamente.');
            },
            error: function () {
                alert('Error al eliminar el estudiante.');
            }
        });
    }
}

$(document).ready(function () {
    cargarEstudiantes();
});
