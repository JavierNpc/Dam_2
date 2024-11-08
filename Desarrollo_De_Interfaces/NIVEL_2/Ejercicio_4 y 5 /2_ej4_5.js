function añadirElemento(){
   const nuevoElemento = document.createElement("li") 
   var texto = document.getElementById("caja").value
   nuevoElemento.textContent =  texto
   nuevoElemento.setAttribute("id", "e")
   document.getElementById("lista").appendChild(nuevoElemento)
   
}

function borrar(){
   document.getElementById("e").remove()
}

function eliminarElemento(){
   document.getElementById("par").innerHTML = "Selecciona un elemnrto para borrarlo"
   const lista = document.getElementById("lista")
   var items = lista.getElementsByTagName("li")

   for (let index = 0; index < lista.length; index++) {
      items[index].setAttribute("onclick()", borrar())
      
   }

  


   
  
  
   

}