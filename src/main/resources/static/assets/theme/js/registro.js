
        async function crearLubricentro(){

        let datos = {};
        

        datos.marca = document.getElementById('txtMarca').value.trim();
        datos.slogan = document.getElementById('txtSlogan').value.trim();
        datos.contacto = document.getElementById('txtContacto').value.trim();
        datos.mail = document.getElementById('txtEmail').value.trim();
        datos.calle = document.getElementById('txtCalle').value.trim();
        datos.altura = document.getElementById('txtNumero').value.trim();
        datos.localidad = document.getElementById('txtLocalidad').value.trim();
        datos.telefono = document.getElementById('txtTelefono').value.trim();
        datos.sitioWeb = document.getElementById('txtSitioWeb').value.trim();
        
        const lubriCentro = await fetch('api/lubricentro', {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
        });
        
        const lubri = await lubriCentro.json();

        const errorValidacion = await fectch('api/lubricentro', {
        method: 'GET',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        });

        const error = errorValidacion.json();

        var datosError = document.getElementById("errorTxt").value.trim();
        
        datosError.textContent = error

        console.log(error);
        }