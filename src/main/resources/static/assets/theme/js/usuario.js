
        cargarUsuario();

        async function cargarUsuario(){
        
        const rawResponse = await fetch('/lubricentro', {
        method: 'GET',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        });
        
        const usuario = await rawResponse.json();

        var contactoElemento = document.getElementById("nombre");
        var sloganElemento = document.getElementById("slogan");

        contactoElemento.textContent = usuario.marca;
        sloganElemento.textContent = usuario.slogan;
        console.log('error');
        }