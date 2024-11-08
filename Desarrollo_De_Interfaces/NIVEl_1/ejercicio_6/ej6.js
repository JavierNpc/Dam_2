function añadirElemento(){
   const nuevoElemento = document.createElement("li") 
   nuevoElemento.textContent = " Nuevo Elemento"
   document.getElementById("lista").appendChild(nuevoElemento)
   
}

function eliminarElemento(){
   document.getElementById("lista").lastChild.remove()
}

function contarElementos(){
   const cantidad = document.getElementById("lista").getElementsByTagName("li").length
   document.getElementById("contador").innerHTML = cantidad


}