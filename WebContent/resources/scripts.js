function enableDisableChbx() {
	var clave_chbx = document.getElementById('form:clave_chbx').checked;
	var nombre_chbx = document.getElementById('form:nombre_chbx').checked;
	var genero_chbx = document.getElementById('form:genero_chbx').checked;
	var plataforma_chbx = document.getElementById('form:plataforma_chbx').checked;
	var precio_chbx = document.getElementById('form:precio_chbx').checked;
	
	document.getElementById('form:clave').disabled = !clave_chbx;
	document.getElementById('form:nombre').disabled = !nombre_chbx;
	document.getElementById('form:genero').disabled = !genero_chbx;
	document.getElementById('form:plataforma').disabled = !plataforma_chbx;
	document.getElementById('form:precio').disabled = !precio_chbx;
}

function resetAll() {
	document.getElementById('form:clave').value = 0;
	document.getElementById('form:nombre').value = "";
	document.getElementById('form:genero').value = "";
	document.getElementById('form:plataforma').value = "";
	document.getElementById('form:precio').value = 0.0;
}